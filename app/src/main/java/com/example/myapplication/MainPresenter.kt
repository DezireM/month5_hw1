package com.example.myapplication

class MainPresenter {

    private val model = CounterModel()
    private var contract: CounterContract? = null

    fun attachContract(contract: CounterContract) {
        this.contract = contract
        contract.showResult(model.count)
    }

    fun onIncrement(){
        model.increment()
        contract?.showResult(model.count)
    }

    fun onDecrement(){
        model.decrement()
        contract?.showResult(model.count)
    }

    private fun checkConditions() {
        val count = model.count
        contract?.showResult(count)

        when (count) {
            10 -> contract?.showToast("Поздравляем")
            15 -> contract?.changeTextColor(android.graphics.Color.GREEN)
        }
    }

    fun detachContract() {
        contract = null
    }
}