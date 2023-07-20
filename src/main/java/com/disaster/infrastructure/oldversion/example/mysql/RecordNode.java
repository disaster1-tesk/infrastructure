package com.disaster.infrastructure.oldversion.example.mysql;

public class RecordNode<V>{
    private int recordType;
    private RecordNode<V> nextRecord;
    private V object;
}
