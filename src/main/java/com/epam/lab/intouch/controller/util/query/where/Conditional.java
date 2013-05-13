package com.epam.lab.intouch.controller.util.query.where;

import com.epam.lab.intouch.controller.util.query.Writable;

public interface Conditional extends Writable {
	Boolean isValid();
}
