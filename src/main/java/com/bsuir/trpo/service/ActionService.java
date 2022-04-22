package com.bsuir.trpo.service;

import java.util.HashMap;

public interface ActionService {

    HashMap<String, Object> execute(HashMap<String, Object> params);
}
