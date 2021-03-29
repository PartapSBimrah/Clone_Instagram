package site.yoonsang.instaclone.src.signup.email

import site.yoonsang.instaclone.src.signup.email.models.EmailSignUpResponse

interface EmailFragmentView {

    fun onPostEmailSignUpSuccess(response: EmailSignUpResponse)

    fun onPostEmailSignUpFailure(message: String)
}