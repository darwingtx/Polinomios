package polinomios;

public class Nodo {
       //atributos
       private int coe, exp;
       private Nodo Liga =null;

        public Nodo(){
            coe=0;
            exp=0;
            Liga=null;
        }
         public Nodo(int coe, int exp){
            this.coe=coe;
            this.exp=exp;
        }
        public int getCoe() {
            return coe;
        }
        public void setCoe(int coe) {
            this.coe = coe;
        }
        public int getExp() {
            return exp;
        }
        public void setExp(int exp) {
            this.exp = exp;
        }
        public Nodo getLiga() {
            return Liga;
        }
        public void setLiga(Nodo liga) {
            Liga = liga;
        }




}
