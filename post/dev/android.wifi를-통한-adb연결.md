# android. wifi를 통한 adb연결

20190329



루팅된 단말의 경우 usb 케이블 연결이 필요하지 않습니다.

활성화

```bash
su
setprop service.adb.tcp.port 5555
stop adbd
start adbd
```

비활성화

```bash
setprop service.adb.tcp.port -1
stop adbd
start adbd
```



루팅되지 않은 단말의 경우에는 USB디버깅 활성화되고 케이블을 통해 단말에 연결된 상태에서 다음과 같이 해줘야 합니다.

```bash
adb tcpip 5555
```



위와 같이 설정된 상태에서, 같은 네트워크상의 호스트 단말에 연결하는 방법은 다음과 같습니다.

```bash
adb connect 192.168.43.1:5555
adb disconnect 192.168.43.1:5555
```



