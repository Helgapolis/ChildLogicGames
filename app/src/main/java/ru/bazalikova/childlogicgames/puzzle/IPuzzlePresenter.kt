package ru.bazalikova.childlogicgames.puzzle

interface IPuzzlePresenter
{
    fun onViewCreated()
    fun onStart()
    fun onStop()
    fun onAnswerBtnClicked(btnIndex: Int, answer: String)
    fun onNextBtnClicked()
    fun onCancelBtnClicked()
}