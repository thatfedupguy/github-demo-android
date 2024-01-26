package com.example.github_demo_android.repo.repoImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.github_demo_android.api.ApiService
import com.example.github_demo_android.api.UserListApi
import com.example.github_demo_android.data.responseModels.User
import com.example.github_demo_android.paging.PagingSource
import com.example.github_demo_android.repo.UserListRepo
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


class UserListRepoImpl @Inject constructor(
    private val apiService: ApiService
) : UserListRepo {

    val api = apiService.buildService(UserListApi::class.java)

    override fun getFollowers(
        username: String,
    ): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                PagingSource { pageCount, pageSize, _ ->
                    api.getFollowers(
                        username = username,
                        pageSize = pageSize,
                        page = pageCount
                    ).body()?.toList().orEmpty()
                }
            }
        ).flow
    }

    override fun getFollowingList(
        username: String
    ): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                PagingSource { pageCount, pageSize, _ ->
                    api.getFollowingList(
                        username = username,
                        pageSize = pageSize,
                        page = pageCount
                    ).body()?.toList().orEmpty()
                }
            }
        ).flow
    }
}