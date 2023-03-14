package org.example;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        StudentServiceIfc studentServiceIfc = createStudentService();

        studentServiceIfc.findAllStudents().forEach(System.out::println);

        System.out.println("---");

        studentServiceIfc.findAllStudents().forEach(System.out::println);
    }

    private static StudentServiceIfc createStudentService() {
        return (StudentServiceIfc) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{StudentServiceIfc.class},
                new CacheableInvocationHandler(new StudentService())
        );
    }
}