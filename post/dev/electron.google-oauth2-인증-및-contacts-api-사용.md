# electron. google oauth2 인증 및 contacts api 사용

20171021



Electron을 이용해 개발한 데스크탑용 앱에서 google oauth2 인증 및 contacts api 를 사용하는 방법을 정리해 보았습니다.



### 구글 API콘솔 설정

가. 구글 API 콘솔에서 Contacts API를 사용하도록 설정하고 '기타' 유형으로 사용자 인증정보를 생성해 다운로드합니다.

나. 개발중인 Electron 앱에 node.js용의 googleapis 와 google-auth-library 를 npm을 이용해 설치합니다.

참고 :

<http://qiita.com/saisai/items/a0a17a9b0a0a7b6f3b5a>

<https://github.com/zico07/kaigiList>

<https://developers.google.com/google-apps/calendar/quickstart/nodejs>



### 구글 인증 관련 클라이언트 코드

```javascript
var google = require( 'googleapis' );
var googleAuth = require( 'google-auth-library' );

var SCOPES = [
    'https://www.googleapis.com/auth/contacts.readonly'
];
var oauth2Client = null;

//여기에서 wvGoogleAuth 는 Electron에서 제공하는 webview 객체입니다.'
//url이 변경될 때 발생하는 will-navigate 이벤트를 핸들링하여 인증결과를 저장하게 하였습니다.
//참고 http://electron.atom.io/docs/api/web-view-tag/
var wvGoogleAuth = document.getElementById( 'wvGoogleAuth' );
wvGoogleAuth.addEventListener( 'will-navigate', function( e ){
    var prefix = 'http://localhost/callback?code=';
    var url = e.url;
    if( url.indexOf( prefix ) === 0 ){
        var code = url.substring( prefix.length );
        oauth2Client.getToken( code, function( error, token ){
            if( error ){
                alert( error );
            }else{
                oauth2Client.credentials = token;
                localStorage.setItem( 'google_token_save_key', JSON.stringify( token ) );
                //TODO : 사용자 UI 업데이트 및 이후 프로세스 진행 처리
            }
        } );
    }
} );

function initAuthClient(){
    var clientSecret = 'xxxx';
    var clientId = 'yyyy';
    var redirectUrl = 'http://localhost/callback';
    var auth = new googleAuth();
    oauth2Client = new auth.OAuth2( clientId, clientSecret, redirectUrl );
    var token = getSavedAuthToken();
    if( token ){
        oauth2Client.credentials = token;
    }
}
function getSavedAuthToken(){
    var tokenStr = localStorage.getItem( 'google_token_save_key' );
    return tokenStr ? JSON.parse( tokenStr ) : null;
}
function isAuthTokenReady(){
    return oauth2Client.credentials && Object.keys( oauth2Client.credentials ).length > 0;
}

//인증 클라이언트를 초기화하고 저장된 인증토큰이 있는지 확인후 
//인증되지 않은 상태이면 사용자가 로그인후 권한허용하는 UI를 Electron 웹뷰에 표시합니다.
initAuthClient();
if( !isAuthTokenReady() ){
    wvGoogleAuth.src = oauth2Client.generateAuthUrl( {
        scope: GOOGLE_SCOPES
    } );
}
```

### 구글 Contacts API 사용 관련 클라이언트 코드

```javascript
function promiseInitGoogleContacts(){
    return new Promise( function( resolve, reject ){
        var t = getSavedAuthToken();
        var c = new GoogleContacts( {
            token: t.access_token,
            refreshToken : t.refresh_token
        } );
        c.getContacts( function( error, data ){
            if( error ){
                console.error( error );
            }
            resolve( data );
        }, {} );
    } );
}
```

구글 GoggleContacts 클래스는 다음과 같은 코드를 가집니다.

<https://github.com/olalonde/Google-Contacts>

```javascript
var EventEmitter = require('events').EventEmitter,
 _ = require('lodash'),
 qs = require('querystring'),
 util = require('util'),
 url = require('url'),
 https = require('https'),
 debug = require('debug')('google-contacts');

var GoogleContacts = function (params) {
 if (typeof params === 'string') {
  params = {token: params}
 }
 if (!params) {
  params = {};
 }

 this.contacts = [];
 this.consumerKey = params.consumerKey ? params.consumerKey : null;
 this.consumerSecret = params.consumerSecret ? params.consumerSecret : null;
 this.token = params.token ? params.token : null;
 this.refreshToken = params.refreshToken ? params.refreshToken : null;

 this.params = _.defaults(params, {thin: true});
};

GoogleContacts.prototype = {};

util.inherits(GoogleContacts, EventEmitter);

GoogleContacts.prototype._get = function (params, cb) {
 if (typeof params === 'function') {
  cb = params;
  params = {};
 }

 var req = {
  host: 'www.google.com',
  port: 443,
  path: this._buildPath(params),
  method: 'GET',
  headers: {
   'Authorization': 'OAuth ' + this.token,
   'GData-Version': 3
  }
 };

 debug(req);

 https.request(req, function (res) {
   var data = '';

   res.on('data', function (chunk) {
    debug('got ' + chunk.length + ' bytes');
    data += chunk.toString('utf-8');
   });

   res.on('error', function (err) {
    cb(err);
   });

   res.on('end', function () {
    if (res.statusCode < 200 || res.statusCode >= 300) {
     var error = new Error('Bad client request status: ' + res.statusCode);
     return cb(error);
    }
    try {
     debug(data);
     cb(null, JSON.parse(data));
    }
    catch (err) {
     cb(err);
    }
   });
  })
  .on('error', cb)
  .end();
};

GoogleContacts.prototype.getContacts = function (cb, params) {
 var self = this;

 this._get(_.extend({type: 'contacts'}, params, this.params), receivedContacts);
 function receivedContacts(err, data) {
  if (err) return cb(err);

  var feed = _.get(data, 'feed', []);
  var entry = _.get(data, 'feed.entry', []);
  if (!entry.length) {
   return cb(null, entry);
  }

  self._saveContactsFromFeed(feed);

  var next = false;
  _.each(feed.link, function (link) {
   if (link.rel === 'next') {
    next = true;
    var path = url.parse(link.href).path;
    self._get({path: path}, receivedContacts);
   }
  });
  if (!next) {
   cb(null, self.contacts);
  }
 }
};

GoogleContacts.prototype.getContact = function (cb, params) {
 var self = this;

 if(!_.has(params, 'id')){
  return cb("No id found in params");
 }

 this._get(_.extend({type: 'contacts'}, this.params, params), receivedContact);

 function receivedContact(err, contact) {
  if (err) return cb(err);

  cb(null, contact);
 }

};

GoogleContacts.prototype._saveContactsFromFeed = function (feed) {
 var self = this;
 _.each(feed.entry, function (entry) {
  var el, url;
  if (self.params.thin) {
   url = _.get(entry, 'id.$t', '');
   el = {
    name: _.get(entry, 'title.$t'),
    email: _.get(entry, 'gd$email.0.address'), // only save first email
    phoneNumber: _.get(entry, 'gd$phoneNumber.0.uri', '').replace('tel:', ''),
    id: url.substring(_.lastIndexOf(url, '/') + 1)
   };
  } else {
   el = entry;
  }
  self.contacts.push(el);
 });
};

GoogleContacts.prototype._buildPath = function (params) {
 if (params.path) return params.path;

 params = _.extend({}, params, this.params);
 params.type = params.type || 'contacts';
 params.alt = params.alt || 'json';
 params.projection = params.projection || (params.thin ? 'thin' : 'full');
 params.email = params.email || 'default';
 params['max-results'] = params['max-results'] || 10000;

 var query = {
  alt: params.alt
 };

 if(!params.id) query['max-results'] = params['max-results'];

 if (params['updated-min'])
  query['updated-min'] = params['updated-min'];

 if (params.q || params.query)
  query.q = params.q || params.query;

 var path = '/m8/feeds/';
 path += params.type + '/';
 path += params.email + '/';
 path += params.projection;
 if(params.id) path +=  '/'+ params.id;
 path += '?' + qs.stringify(query);

 return path;
};

GoogleContacts.prototype.refreshAccessToken = function (refreshToken, params, cb) {
 if (typeof params === 'function') {
  cb = params;
  params = {};
 }

 var data = {
  refresh_token: refreshToken,
  client_id: this.consumerKey,
  client_secret: this.consumerSecret,
  grant_type: 'refresh_token'

 };

 var body = qs.stringify(data);

 var opts = {
  host: 'accounts.google.com',
  port: 443,
  path: '/o/oauth2/token',
  method: 'POST',
  headers: {
   'Content-Type': 'application/x-www-form-urlencoded',
   'Content-Length': body.length
  }
 };

 var req = https.request(opts, function (res) {
  var data = '';
  res.on('end', function () {
   if (res.statusCode < 200 || res.statusCode >= 300) {
    var error = new Error('Bad client request status: ' + res.statusCode);
    return cb(error);
   }
   try {
    data = JSON.parse(data);
    cb(null, data.access_token);
   }
   catch (err) {
    cb(err);
   }
  });

  res.on('data', function (chunk) {
   data += chunk;
  });

  res.on('error', cb);

 }).on('error', cb);

 req.write(body);
 req.end();
};

module.exports = GoogleContacts;
```

