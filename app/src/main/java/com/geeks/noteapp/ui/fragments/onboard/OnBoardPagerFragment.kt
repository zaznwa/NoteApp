package com.geeks.noteapp.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.geeks.noteapp.R
import com.geeks.noteapp.databinding.FragmentOnBoardPagerBinding

class OnBoardPagerFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        when (requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0 -> {
                Glide.with(this@OnBoardPagerFragment)
                    .asGif()
                    .load("https://s3-alpha-sig.figma.com/img/5722/98cb/925f5eb4f5fe0c07adda040bd75b2727?Expires=1737936000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=X6rQqhSywmIKcQ7wXvx4ryM0WOgikbcL8LsUfvtNtqDMeHeAPoWYCx7bqufIP2Ln2WW46IupQbWN7s96nWCIkZhRXyQ72fdPWCd92VHeJPL90LTKc~vz89GWnOiTKhQIVkD34W5l0DPyODp-yB~MdtcBszo-pHdwKclOJnutZtHYA0iZ3CGs~V4zgcO19XUhsTv0uPmVA8frNPsAdX1FHsgFTpTYBPiIvxPIkocIRie83yQiVM5uLod4bv2tQ-lcPEITUXrffVQuZs0U2oUHmuvZHtxzYxISyTIJtCzx1ANlr90yisqHG2ZkEDSL34C9r7fQR-QyuhvgDfd28vQL4A__")

                    .into(gifOnboard)

                txtOnboardTittle.text = "Удобство"
                txtOnboardSubtittle.text =
                    "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно."
            }

            1 -> {
                Glide.with(this@OnBoardPagerFragment)
                    .asGif()
                    .load("https://s3-alpha-sig.figma.com/img/ecee/4655/a706adaaf21259444457321bd362df20?Expires=1737936000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=kn7v-FVeqfUxdVPmiBSunvgqSSqW2Fbb2ZyTqGzlAaosMLOwc5gRGvfDVwmQ4Zf83qyawM5AcpUQGv4snYEIvr5IkPuMTfIUtq9VH-YsBzZ-RYD2p18DYGRKfHhByA1NCJ8shOEAckn7VPiWTTqf1VIkHk2CkTyfVJyldvZkJ70FKLeX0nSyHWWICWCTmw0jydCFQZjLOt2pz~HfAdGKxlUTlNyAxZplpFCkqRu9HSaImjF6fUqCU13KC8W3oqGOLoMVlHSyeaJ2pGzOBooyOuUciuS6wel3MRHYdPog6xabkooKMTIYDZzgpPgAbajMkQAw2vTmsPp0DHcYPSZi8g__")

                    .into(gifOnboard)

                txtOnboardTittle.text = "Организация"
                txtOnboardSubtittle.text =
                    "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
            }

            2 -> {
                Glide.with(this@OnBoardPagerFragment)
                    .asGif()
                    .load("https://s3-alpha-sig.figma.com/img/5bc0/21c2/b705755e5a87fa01bc1de6f9a54c7e17?Expires=1737936000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=DZZlGZxR8N2CjaVxEL2ML7rqC8JvjRYoSc83EVgsNjA2eZCeo5uQYqEgjl2l17KNvd290iVQt8C~c7lTXynaqxNeOrnU9R3ltBo7y9LcXKPO~2yHM4XSN-fhkvmz~2CnHPgE2jV3zMJuvCi3YRtiln5ji0KUuRRF~CT8Hf6bOgYg61ljRumROFszMcXoAnsOZkDwaatvwIA6y0fRdcU24kYf1FReMsNTLBE-qH7MG--ze6A53XR-YgQMB0LWbOtlqA5FA05~oOD6Cnhg27KGNIZv-EepH9I2rWCAfge56NaUSDGNrwJgz9iq1zQyQeKACp68CxXIgv2s8AsK0l7SQQ__")
                    .into(gifOnboard)

                txtOnboardTittle.text = "Синхронизация "
                txtOnboardSubtittle.text =
                    "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
            }
        }
    }

    companion object {
        const val ARG_ONBOARD_POSITION = "onBoard"
    }
}

