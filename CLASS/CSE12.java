public class CSE12{
    private int numStudents;
    public CSE12(){
        numStudents = 0;
    }
    public CSE12(int num){
        numStudents = num;
    }

    public int getNum(){
        return numStudents;
    }
    public int officeHour(){
        return numStudents/8;
    }
    public static void main(String[] args){
        CSE12 ref = new CSE12(660);
        System.out.println(ref.getNum());
    }
}