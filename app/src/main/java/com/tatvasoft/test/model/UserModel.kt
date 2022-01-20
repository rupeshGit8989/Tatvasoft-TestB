package com.tatvasoft.test.model


class UserModel(
    var page: Int,
    var per_page: Int,
    var total: Int,
    var total_pages: Int,
    var data: List<UserItem>
) {
}