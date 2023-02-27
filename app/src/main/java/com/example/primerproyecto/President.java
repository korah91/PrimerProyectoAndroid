package com.example.primerproyecto;

import java.util.Comparator;

public class President {
    private int id;
    private String name;
    private int date;
    private String url;

    public President(int id, String name, int date, String url) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.url = url;
    }

    @Override
    public String toString() {
        return "President{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", url='" + url + '\'' +
                '}';
    }
    // Comparador para nombre de A a Z
    public static Comparator<President> PresidentNameAZComparator = new Comparator<President>() {
        @Override
        public int compare(President p1, President p2) {
            return p1.getName().compareTo(p2.getName());
        }
    };
    // Comparador para nombre de Z a A
    public static Comparator<President> PresidentNameZAComparator = new Comparator<President>() {
        @Override
        public int compare(President p1, President p2) {
            return p2.getName().compareTo(p1.getName());
        }
    };

    // Comparador para fecha ascendente
    public static Comparator<President> PresidentDateASCComparator = new Comparator<President>() {
        @Override
        public int compare(President p1, President p2) {
            return p1.getDate() - p2.getDate();
        }
    };

    // Comparador para fecha descendente
    public static Comparator<President> PresidentDateDESCComparator = new Comparator<President>() {
        @Override
        public int compare(President p1, President p2) {
            return p2.getDate() - p1.getDate();
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
