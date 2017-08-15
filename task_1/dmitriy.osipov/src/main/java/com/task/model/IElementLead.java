package com.task.model;

import java.util.Map;

public interface IElementLead extends IElement {

    Map<String, IElementContent> getContent();

    void addContent(IElementContent content);

}
