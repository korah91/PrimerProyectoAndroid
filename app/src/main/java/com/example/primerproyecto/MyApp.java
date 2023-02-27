package com.example.primerproyecto;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// ESTACLASE ES PARA PONER LA LISTA DE PRESIDENTES COMO VARIABLE GLOBAL, CUANDO HAGA CON SQLLITE TENGO QUE HACERLO DE OTRA FORMA
public class MyApp extends Application {
    private static List<President> presidentList = new ArrayList<President>();
    // Siguiente id
    private static int nextId = 10;

    public MyApp() {
        fillPresidentList();
    }

    private void fillPresidentList() {
        President p0 = new President(0, "Jorge wasinton", 1964, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Gilbert_Stuart_Williamstown_Portrait_of_George_Washington.jpg/220px-Gilbert_Stuart_Williamstown_Portrait_of_George_Washington.jpg");
        President p1 = new President(1, "John Adames", 1905, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Gilbert_Stuart_Williamstown_Portrait_of_George_Washington.jpg/220px-Gilbert_Stuart_Williamstown_Portrait_of_George_Washington.jpg");
        President p2 = new President(2, "Pepe", 2012, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Gilbert_Stuart_Williamstown_Portrait_of_George_Washington.jpg/220px-Gilbert_Stuart_Williamstown_Portrait_of_George_Washington.jpg");

        presidentList.addAll(Arrays.asList( new President[] {p0, p1, p2} ));
    }

    public static List<President> getPresidentList() {
        return presidentList;
    }

    public static void setPresidentList(List<President> presidentList) {
        MyApp.presidentList = presidentList;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        MyApp.nextId = nextId;
    }
}
