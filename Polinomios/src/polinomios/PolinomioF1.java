package polinomios;

import javax.swing.JOptionPane;

public class PolinomioF1 {

    // Atributos
    private int[] vect;
    private int du;

    //Constructor Forma 1
    public PolinomioF1(int du) {
        this.du = du;
        vect = new int[du + 1];
    }

    public void ConvertF1(String Vs[]) {
        
        vect[0] = du - 1;
        int pos = 0;
        
        for (int i = 0;  i+1<Vs.length && Vs[i] != null; i += 2) {
            
            pos = du - Integer.parseInt(Vs[i + 1]);
            vect[pos] = vect[pos] + Integer.parseInt(Vs[i]);
        }
    }

    public void Ajustar() {
        
        int r = 1, i = 2;
        
        while (vect[i] == 0) {
            
            i++; r++; 
        }
        
        while (i <= du) {
            
            vect[i - r] = vect[i];
            i++;
        }
        du = du - r;
        this.setVect(0, du - 1);
        this.Redimensionar();
    }

    public void Redimensionar() {
        
        int[] vectaux = new int[du + 1];
        int i = 0;
        
        while (i < du + 1) {
            
            vectaux[i] = vect[i];
            i++;
        }
        vect = vectaux;
    }

    public void Mostrarmenor() {
        
        int i = du, aux = 0, exp = 0;
        
        while (i >= 1) {
            
            if (vect[i] != 0) {
                
                aux = vect[i];
                exp = du - i;
                i = 0;
            }
            i--;
        }
        System.out.println(aux + "^" + exp);
    }

    public void MostrarF1() {
        
        String s = "";
        
        for (int i = 0; i < vect.length; i++) {
            
            s = s + "[" + vect[i] + "]";
        }
        JOptionPane.showMessageDialog(null, s);
    }

    public void MostrarP() {
        
        StringBuilder s = new StringBuilder();

        for (int i = 1; i <= du; i++) {
            
            int exp = du - i;
            int coefficient = vect[i];

            if (coefficient != 0) {
                
                if ((i != 1) && coefficient > 0) {
                    
                    s.append("+");
                }

                if (exp > 1) {
                    
                    if (coefficient != 1 && coefficient != -1) {
                        
                        s.append(coefficient);
                        
                    } else if (coefficient == -1) {
                        
                        s.append("-");
                    }
                    s.append("x^").append(exp);
                    
                } else if (exp == 1) {
                    
                    if (coefficient != 1 && coefficient != -1) {
                        
                        s.append(coefficient);
                        
                    } else if (coefficient == -1) {
                        
                        s.append("-");
                    }
                    s.append("x");
                    
                } else if (exp == 0) {
                    
                    s.append(coefficient);
                }
            }
        }
        JOptionPane.showMessageDialog(null, s.toString());
    }

    public void Evaluar(int x) {
        
        int resultado = 0;
        
        for (int i = 1; i <= du; i++) {
            
            int exp = du - i;
            resultado = (int)(resultado + (vect[i] * Math.pow(x, exp)));
        }
        JOptionPane.showMessageDialog(null, "El resultado del polinomio evaluando con "
                + x
                + " es = "
                + resultado);
    }

    public void Sumar(PolinomioF1 B) {
        
        PolinomioF1 C = null;
        
        if (this.vect[0] > B.vect[0]) {
            
            C = new PolinomioF1(this.du + 2);
            
        } else {
            
            C = new PolinomioF1(B.du + 2);
        }

        int i = 1, j = 1, expA = 0, expB = 0;

        while ((i < this.vect.length && j < B.vect.length) && i <= C.du) {
            
            expA = this.du - i;
            expB = B.du - j;
            
            if (expA > expB) {
                
                C.vect[C.du - expA] = this.vect[i];
                i++;
                
            } else if (expA < expB) {
                
                C.vect[C.du - expB] = B.vect[j];
                j++;
                
            } else {
                
                C.vect[C.du - expB] = B.vect[j] + this.vect[i];
                i++; j++;
            }
        }
        if(C.vect[0]!=0 && C.vect[1]!=0){
        C.Ajustar();
        C.MostrarP();
         }else {
         JOptionPane.showInternalMessageDialog(null, "0");
        }

    }

    public void InsertarM(PolinomioF1 B) {
        
        int pos = 0;
        
        if (this.getVect(0) == B.getVect(0)) {
            
            this.setVect(1, this.getVect(1) + B.getVect(1));
            
            if (this.getVect(1) == 0) {
                
                this.Ajustar();
            }
            
        } else {
            
            if (this.getVect(0) > B.getVect(0)) {
                
                pos = this.du - B.getVect(0);
                this.setVect(pos, this.getVect(pos) + B.getVect(1));
            }
            
            if (this.getVect(0) < B.getVect(0)) {
                
                for (int j = 1; j <= this.getDu(); j++) {

                    pos = B.du - (this.du - j);
                    B.setVect(pos, B.getVect(pos) + this.getVect(j));

                }
                this.setVect(B.getVect());
                this.setDu(B.getDu());
            }
        }
    }

    public void Restar(PolinomioF1 B) {
        
        PolinomioF1 C = null;
        
        if (this.vect[0] > B.vect[0]) {
            
            C = new PolinomioF1(this.du + 2);
            
        } else {
            
            C = new PolinomioF1(B.du + 2);
        }

        int i = 1, j = 1, expA = 0, expB = 0;

        while ((i < this.vect.length && j < B.vect.length) && i <= C.du) {
            
            expA = this.du - i;
            expB = B.du - j;
            
            if (expA > expB) {
                
                C.vect[C.du - expA] = this.vect[i];
                i++;
                
            } else if (expA < expB) {
                
                C.vect[C.du - expB] = -1 * B.vect[j];
                j++;
                
            } else {
                
                C.vect[C.du - expB] = this.vect[i] - B.vect[j];
                i++; j++;
            }
        }
        if(C.vect[0]!=0 && C.vect[1]!=0){
        C.Ajustar();
        C.MostrarP();
         }else {
         JOptionPane.showInternalMessageDialog(null, "0");
        }
    }

    public void Eliminar(PolinomioF1 E) {

        int pos = this.du - E.getVect(0);
        boolean swt = false;

        if (this.getVect(0) >= E.getVect(0)) {

            if (this.du - pos == E.getVect(0)) {

                if (this.getVect(pos) == E.getVect(1)) {

                    this.setVect(pos, 0);
                    swt = true;
                    EliminarTrue(swt);

                    if (this.getVect(1) == 0) {

                        Ajustar();
                    }
                }
            }
        }
    }
    
    public void EliminarTrue(boolean sw){
        
        if(sw = true){
            
           JOptionPane.showMessageDialog(null,"Eliminado Con Exito");
           
        } else {
            
            JOptionPane.showMessageDialog(null,"No Se EncontrÃ³ El Monomio Ingresado");
        }
    }
    
    public void Multiplicar(PolinomioF1 B) {
        
        PolinomioF1 C = null;

        if (this.vect[0] > B.vect[0]) {
            
            C = Distributiva(this, B);
            
        } else {
            
            C = Distributiva(B, this);
        }
        C.Ajustar();
        C.MostrarP();
    }

    public PolinomioF1 Distributiva(PolinomioF1 n, PolinomioF1 m) {
        
        int expn = 0, expm = 0, expc = 0, pos = 0;
        PolinomioF1 C = new PolinomioF1(m.vect[0] + n.vect[0] + 2);
        C.vect[0] = m.vect[0] + n.vect[0];
        
        for (int j = 1; j <= m.du; j++) {
            
            if (m.vect[j] != 0) {
                
                for (int i = 1; i <= n.du; i++) {
                    
                    expn = n.du - i;
                    expm = m.du - j;
                    expc = expm + expn;
                    pos = C.du - expc;
                    C.vect[pos] = n.vect[i] * m.vect[j];
                }
            }
        }
        return C;
    }

    public int[] getVect() {
        return vect;
    }

    public void setVect(int[] vect) {
        this.vect = vect;
    }

    public int getVect(int i) {
        return vect[i];
    }

    public void setVect(int i, int d) {
        this.vect[i] = d;
    }

    public int getDu() {
        return du;
    }

    public void setDu(int du) {
        this.du = du;
    }
    
}