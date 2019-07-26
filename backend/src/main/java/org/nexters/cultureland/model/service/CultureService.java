package org.nexters.cultureland.model.service;

import java.util.List;

public interface CultureService {
    public List getAllCulture();
    public List getCultureByKind(String cultureName);
}
