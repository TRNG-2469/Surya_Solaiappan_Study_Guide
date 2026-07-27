package com.cohort.alpha;

public class PackageNeighbor {
    public static void main(String[] args){
        AccessHolder holder = new AccessHolder();
        holder.publicMethod();
        holder.protectedMethod();
        holder.defaultMethod();
        // holder.privateMethod(); Can you compile this?
        // The last line cannot be compiled because it is a private field.
    }
}
