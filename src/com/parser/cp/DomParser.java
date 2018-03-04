package com.parser.cp;

import com.parser.cp.exception.ImpartialException;
import com.parser.cp.model.Task;

public interface DomParser {
    Task parse(String domAsString) throws ImpartialException;
}
