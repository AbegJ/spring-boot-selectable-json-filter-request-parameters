package com.abegglen

import com.fasterxml.jackson.annotation.JsonFilter
import com.fasterxml.jackson.annotation.JsonView
import groovy.transform.ToString;

@ToString
class Greeting {

    interface Summary {}
    interface SummaryWithFullName extends Summary {}
    interface SummaryComplete extends SummaryWithFullName{}

    @JsonView(Summary.class)
    long id

    @JsonView(Summary.class)
    String content

    @JsonView(SummaryWithFullName.class)
    String name

    @JsonView(SummaryComplete.class)
    String address

}
