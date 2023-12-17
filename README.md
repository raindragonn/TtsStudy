# [Android] Text To Speech (TTS)

## TTS란?

Text To Speech의 약자로 텍스트를 음성으로 출력해주는 것을 말한다.

본 예제는 안드로이드에서 기본적으로 지원해주는 `Android.speech.tts` 를 사용한다.

## 사용법

사용 방법은 초기화, 설정, 출력으로  매우 간단하다.

### 1. 초기화

`TextToSpeech(Context context, OnInitListener listener)` 를 이용해 초기화 한다.

두번 째 매개변수 `listener` 는 해당 TTS의 초기화 완료 콜백을 위한 인터페이스이다. 해당 인터페이스의 `OnInit`을 통해 초기화 성공, 실패 여부를 콜백으로 알 수 있다.

### 2. 음성 설정

음성 설정은 부가적인 설정으로, TTS의 음성 설정 등이 가능하다.

- 음성 언어 설정 `TextToSpeech.setLanguage(Locale)`
- 음성 톤 설정 `TextToSpeech.setPitch(Float)`
    - 1.0f 가 기본 설정이며, 낮출 수록 톤이 낮아진다
    - 직접 확인한 바에 따르면 큰 차이는 느끼지 못하겠다.
- 음성 속도 설정 `TextToSpeech.setSpeechRate(float)`
    - 1.0f 가 기본 설정이며 높일수록 음성이 빨라진다.

### 3. 음성 출력

`TextToSpeech.speak(CharSequence text, int queueMode,Bundle params,String utteranceId)` 을 통해 음성을 출력할 수 있다.

**[ 매개변수 설명 ]** 

- text: 음성으로 출력 하고자 하는 텍스트
- queueMode:  재생 대기열의 처리 방법 ( 쉽게 말해 음성 이 출력되는 진행중에 음성 출력을 하는 경우 음성에 대한 처리 방법)
    - *`QUEUE_FLUSH` :  현재 진행중이던 음성 및 대기열을 없애고 새로운 음성을 출력한다.*
    - *`QUEUE_ADD` : 새로운 음성을 대기열에 추가하고 음성이 끝나면 이후 출력한다.*
- params: Volume, Stream 등의 변경을 요청할 수 있으며, 요청할 것이 없는경우 null로 사용한다.
- utteranceId: 유니크한 아이디값

### 참고 자료

---

https://developer.android.com/reference/android/speech/tts/TextToSpeech
