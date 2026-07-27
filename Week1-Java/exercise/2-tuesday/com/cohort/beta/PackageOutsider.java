package com.cohort.beta;

import com.cohort.alpha.AccessHolder;

public class PackageOutsider extends AccessHolder {
    public static void main(String[] args){
        AccessHolder  holder = new AccessHolder();

        holder.publicMethod();
        //holder.protectedMethod();
        // Doesn't work initially but a protected member can be accessed from a different package, but only through inheritance (subclasses)

        // Works if you do this:
        // PackageOutsider outsider = new PackageOutsider();
        //outsider.protectedMethod();

        //holder.defaultMethod();
        //Default fields cannot be accessed in a different package

        //holder.privateMethod();
        //Private fields cannot be accessed in a different package
    }
}
