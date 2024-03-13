public class MyNum {
    protected String value;

    public MyNum(String s){
        value = s;
    }
    public MyNum(){

    }
    public String val(){
        return value;
    }
    public void thousand(int a){
        String s = "";
        for(int i=0; i<1000; i++){
            s += a;
        }
        value = s;
    }
}
