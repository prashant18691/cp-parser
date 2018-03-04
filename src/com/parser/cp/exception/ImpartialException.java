package com.parser.cp.exception;

/**
 * Update a slack channel/write to ODS about the exception.
 * This should not occur unless
 * CP website changed it's DOM structure (or)
 * If we haven't added the parser path
 */
public class ImpartialException extends Exception {
    public ImpartialException(String message) {
        super(message);
    }
}
