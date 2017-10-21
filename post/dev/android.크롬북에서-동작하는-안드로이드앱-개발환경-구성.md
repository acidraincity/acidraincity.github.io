# android. 크롬북에서 동작하는 안드로이드앱 개발환경 구성

20160722



크롬OS 업데이트를 통해서 크롬북에 구글 플레이스토어 설치 및 스토어를 통한 안드로이드앱 설치 및 실행이 지원되게 되었습니다.

개발해 서비스중인 안드로이드앱이 있으면 크롬OS상에서도 정상작동하는지 확인해보고 문제가 있을 경우 디버깅해야 할텐데요.

그 절차를 설명합니다.



### 1.

크롬북은 개발자모드가 활성화되어 있어야 합니다.

크롬북의 개발자모드를 활성화시키는 방법은 아래 포스트를 참고하도록 합시다.

<http://www.howtogeek.com/210817/how-to-enable-developer-mode-on-your-chromebook/>

개발자모드 활성화 후 처음 부팅했을때 셋팅 마법사가 나오는데요.

해당 창의 옵션중 디버깅 기능 활성화를 시키는게 중요합니다.



### 2.

개발자모드가 활성화되었다면 [Ctrl] + [Alt] + [->] 키조합으로 콘솔에 진입할 수 있습니다.

root 아이디와 설정한 root 패스워드로 로그인하고 다음 명령들을 실행합니다.

\#/usr/libexec/debugd/helpers/dev_features_rootfs_verification

\#reboot

\#/usr/libexec/debugd/helpers/dev_features_ssh

자세한 설명은 아래 포스트를 참고하세요.

<https://www.chromium.org/chromium-os/android-apps>



### 3.

크롬OS 설정에서 안드로이드앱 설정으로 들어가서 안드로이드 개발자 옵션을 활성화시킵니다. (빌드넘버 연타)

그리고 안드로이드 개발자 옵션에서 안드로이드 USB디버깅을 허용하도록 설정합니다.



### 4.

이제 다 되었습니다.

개발 PC와 크롬북이 같은 네트워크에 있어야 합니다.

개발 PC에서는 콘솔에 다음과 같이 실행해서 크롬북을 adb 연결합니다.

$adb connect xxx.xxx.xxx.xxx:22

xxx.xxx.xxx.xxx는 네트워크상에서의 크롬북 ip입니다.

이렇게 adb 연결이 되면, 일반 안드로이드 기기에 대해 실행, 디버깅하는 것과 동일하게 앱을 테스트할 수 있습니다.

연결을 해지할 때는 adb disconnect 명령을 사용하면 됩니다.