package com.example.super_news

data class Top_business_headlines(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)