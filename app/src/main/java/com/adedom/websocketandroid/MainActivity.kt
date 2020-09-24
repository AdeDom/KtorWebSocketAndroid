package com.adedom.websocketandroid

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.callApi.observe { message ->
            Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
        }

        viewModel.state.observe { state ->
            progressBar.visibility = if (state.loading) View.VISIBLE else View.INVISIBLE

            if (state.isSendMessage) etMessage.text.clear()

            if (state.isChat) {
                val message = "${state.chat?.name} : ${state.chat?.message}"
                tvMessage.append("$message\n")
            }
        }

        viewModel.error.observeError()

        viewModel.attachFirstTime.observe {
            viewModel.initialize()
            viewModel.fetchChat()
        }

        etMessage.addTextChangedListener {
            viewModel.setStateMessage(it.toString())
        }

        btSendMessage.setOnClickListener {
            viewModel.sendMessage()
        }
    }

}
