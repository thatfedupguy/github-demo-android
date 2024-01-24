package com.example.github_demo_android

import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.github_demo_android.api.ApiResult
import com.example.github_demo_android.data.responseModels.User
import com.example.github_demo_android.repo.ProfileRepo
import com.example.github_demo_android.viewmodel.ProfileViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProfileViewModelTest {

    lateinit var ioDispatcher: CoroutineDispatcher

    @Mock
    lateinit var profileRepo: ProfileRepo

    lateinit var savedStateHandle: SavedStateHandle
    lateinit var emptySavedStateHandle: SavedStateHandle
    lateinit var invalidSavedStateHandle: SavedStateHandle

    private val user = User(
        login = "thatfedupguy",
        id = null,
        avatar_url = null,
        url = null,
        public_repos = null,
        public_gists = null,
        followers = null,
        following = null,
        name = null,
        bio = null,
        message = null
    )

    private val invalidUser = User(
        login = null,
        id = null,
        avatar_url = null,
        url = null,
        public_repos = null,
        public_gists = null,
        followers = null,
        following = null,
        name = null,
        bio = null,
        message = "Not Found"
    )

    @Before
    fun setup(){
        ioDispatcher = UnconfinedTestDispatcher()
        savedStateHandle = SavedStateHandle(mapOf(Pair("login", "thatfedupguy")))
        emptySavedStateHandle = SavedStateHandle(mapOf(Pair("login", "")))
        invalidSavedStateHandle = SavedStateHandle(mapOf(Pair("login", "thajkjke")))
    }

    @Test
    fun `if username is empty string then show error`(){
        runTest {
            val viewModel = ProfileViewModel(emptySavedStateHandle, ioDispatcher, profileRepo)
            viewModel.uiState.test {
                assertEquals(true, awaitItem().isError)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `get user return success for listed username`(){
        runTest {
            doReturn(ApiResult.Success(user)).`when`(profileRepo).getUser("thatfedupguy")
            val viewModel = ProfileViewModel(savedStateHandle, ioDispatcher, profileRepo)
            viewModel.uiState.test {
                assertEquals(user, awaitItem().user)
                cancelAndIgnoreRemainingEvents()
            }
            verify(profileRepo).getUser("thatfedupguy")
        }
    }

    @Test
    fun `get user fails for invalid username`(){
        runTest {
            doReturn(ApiResult.Success(invalidUser)).`when`(profileRepo).getUser("thajkjke")
            val viewModel = ProfileViewModel(invalidSavedStateHandle, ioDispatcher, profileRepo)
            viewModel.uiState.test {
                assertEquals(invalidUser, awaitItem().user)
                cancelAndIgnoreRemainingEvents()
            }
            verify(profileRepo).getUser("thajkjke")
        }
    }

    @Test
    fun `get user throws exception in case of server error`(){
        runTest {
            val errorMessage = "Something went wrong"
            doReturn(ApiResult.Error(message = errorMessage)).`when`(profileRepo).getUser("thatfedupguy")
            val viewModel = ProfileViewModel(savedStateHandle, ioDispatcher, profileRepo)
            viewModel.uiState.test {
                assertEquals(true, awaitItem().isError)
                cancelAndIgnoreRemainingEvents()
            }
            verify(profileRepo).getUser("thatfedupguy")
        }
    }


}