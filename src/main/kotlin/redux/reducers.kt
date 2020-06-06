package redux

import data.*

fun changeReducer(state: State, action: RAction) =
    when (action) {
        is NoFilter -> State(
            state.games.apply {
                forEach { it.isVisible = true }
            }
        )
        is FilterByGenre -> State(
            state.games.apply {
                forEach { it.isVisible = true }
                forEach {
                    if ( it.genre != action.genre ) {
                        it.isVisible = !it.isVisible
                    }
                }
            }
        )
        is FilterByYearCreate -> State(
            state.games.apply {
                forEach { it.isVisible = true }
                forEach {
                    if ( it.dateOfCreation != action.year ) {
                        it.isVisible = !it.isVisible
                    }
                }
            }
        )
        is FilterByRating -> State(
            state.games.apply {
                var a = action.rate
                if (a[a.lastIndex] == '+') {
                    a = a.substringBefore("+")
                }
                console.log("a = ${a.toInt()}")
                    forEach { it.isVisible = true }
                    forEach {
                        if (it.ageRating != a.toInt()) {
                            console.log("true")
                            it.isVisible = !it.isVisible
                        }
                    }
                }
        )
        else -> state
    }
