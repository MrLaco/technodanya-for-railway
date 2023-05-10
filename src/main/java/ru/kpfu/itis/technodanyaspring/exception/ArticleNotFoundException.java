package ru.kpfu.itis.technodanyaspring.exception;

public class ArticleNotFoundException extends Exception {
    public ArticleNotFoundException(String message) {
        super(message);
    }
}
