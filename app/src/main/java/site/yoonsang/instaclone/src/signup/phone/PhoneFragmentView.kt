package site.yoonsang.instaclone.src.signup.phone

import site.yoonsang.instaclone.src.signup.phone.models.PhoneSignUpResponse

interface PhoneFragmentView {

    fun onPostPhoneSignUpSuccess(response: PhoneSignUpResponse)

    fun onPostPhoneSignUpFailure(message: String)
}