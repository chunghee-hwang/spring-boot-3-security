package com.chung.security.demo.common;

public class Constants {

    public enum ExceptionType {

        AUTHENTICATION("Authentication");

        private String exceptionType;

        ExceptionType(String exceptionType) {
            this.exceptionType = exceptionType;
        }

        public String getExceptionType() {
            return exceptionType;
        }

        @Override
        public String toString() {
            return getExceptionType() + "Exception. ";
        }

    }

}
