package fr.istic.vv;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) throws IllegalArgumentException {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (year < 1 || month < 1 || month > 12) {
            return false;
        }
    
        // Nombre de jours par mois
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        // Vérifier si l'année est bissextile
        if (isLeapYear(year) && month == 2) {
            daysInMonth[1] = 29;
        }
    
        // Vérifier le jour du mois
        return day >= 1 && day <= daysInMonth[month - 1];
    }
    

    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }    

    public Date nextDate() {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(this.year)) {
            daysInMonth[1] = 29;
        }
    
        int nextDay = this.day + 1;
        int nextMonth = this.month;
        int nextYear = this.year;
    
        if (nextDay > daysInMonth[this.month - 1]) {
            nextDay = 1;
            nextMonth++;
            if (nextMonth > 12) {
                nextMonth = 1;
                nextYear++;
            }
        }
    
        return new Date(nextDay, nextMonth, nextYear);
    }
    
    public Date previousDate() {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(this.year)) {
            daysInMonth[1] = 29;
        }
    
        int prevDay = this.day - 1;
        int prevMonth = this.month;
        int prevYear = this.year;
    
        if (prevDay < 1) {
            prevMonth--;
            if (prevMonth < 1) {
                prevMonth = 12;
                prevYear--;
            }
            prevDay = daysInMonth[prevMonth - 1];
        }
    
        return new Date(prevDay, prevMonth, prevYear);
    }    

    public int compareTo(Date other) {
        if (other == null) {
            throw new NullPointerException("La date à comparer ne doit pas etre nulle.");
        }
        if (this.year != other.year) {
            return this.year - other.year;
        } else if (this.month != other.month) {
            return this.month - other.month;
        } else {
            return this.day - other.day;
        }
    }    

}
