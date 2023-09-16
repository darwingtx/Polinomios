package polinomios;

import javax.swing.JOptionPane;

public class PolinomioF2 {

    // Atributos
    private int[] vectf2;
    private int du;

    //Constructor Forma 2
    public PolinomioF2(int du) {

        this.du = du;
        this.vectf2 = new int[du + 1];//5
    }

    public void ConvertF2(String[] Vs) {

        vectf2[0] = du / 2;

        for (int i = 0; i < Vs.length && Vs[i] != null; i++) {

            vectf2[i + 1] = Integer.parseInt(Vs[i]);
        }
    }

    public void MostrarF2() {

        String s = "";

        for (int i = 0; i <= du; i++) {

            s = s + "[" + vectf2[i] + "]";

        }
        JOptionPane.showMessageDialog(null, s);
    }

    public void Ajustar() {

        int r = 0, i = 1;

        while (vectf2[i] == 0) {

            i += 2;
            r++;
        }

        while (i <= du) {

            vectf2[i - r - 1] = vectf2[i];
            vectf2[i - r] = vectf2[i + 1];
            i += 2;
        }

        vectf2[0] = vectf2[0] - r;
        du = vectf2[0] * 2;

        this.Redimensionar();
    }

    public void Redimensionar() {

        int[] vectaux = new int[du + 1];
        int i = 0;

        while (i <= du) {

            vectaux[i] = vectf2[i];
            i++;
        }
        vectf2 = vectaux;
    }

    public void RedimensionarAgrandar() {

        int[] vectaux = new int[du + 3];
        int i = 0;

        while (i <= du) {

            vectaux[i] = vectf2[i];
            i++;
        }
        vectf2 = vectaux;
        du = du + 2;
        vectf2[0] += 1;
    }

    public void EvaluarF2(int x) {

        int resultado = 0;

        for (int i = 1; i <= du; i += 2) {
            int exp = vectf2[i + 1];
            resultado = (int) (resultado + (vectf2[i] * Math.pow(x, exp)));
        }
        JOptionPane.showMessageDialog(null, "El resultado del polinomio evaluando con "
                + x
                + " es = "
                + resultado);
    }

    public void SumarF2(PolinomioF2 B) {
        PolinomioF2 C = new PolinomioF2(((this.vectf2[0] + B.vectf2[0]) * 2) + 1);
        int i = 2, k = 2, j = 2;
        while (i <= this.du && j <= B.du) {
            int sum = 0;
            if (this.vectf2[i] > B.vectf2[j]) {
                C.vectf2[k] = this.vectf2[i];//exponente
                C.vectf2[k - 1] = this.vectf2[i - 1];//coeficiente
                i += 2;
                k += 2;
            } else if (B.vectf2[j] > this.vectf2[i]) {
                C.vectf2[k] = B.vectf2[j];
                C.vectf2[k - 1] = B.vectf2[j - 1];
                j += 2;
                k += 2;
            } else {
                sum = this.vectf2[i - 1] + B.vectf2[j-1];
                if (sum != 0) {
                    C.vectf2[k - 1] = sum;
                    C.vectf2[k] = this.vectf2[i];
                }
                i += 2;
                j += 2;
                k += 2;
            }
        }
        C.vectf2[0] = (k - 1) / 2;
        if(C.vectf2[1]!=0){
        C.Ajustar();
        C.MostrarPolinomio();
        }else {
        JOptionPane.showMessageDialog(null, "0");
        }
    }

    public void RestarF2(PolinomioF2 B) {

        PolinomioF2 C = new PolinomioF2(((this.vectf2[0] + B.vectf2[0]) * 2) + 1);

        int i = 2, k = 2, j = 2;

        while (i <= this.du && j <= B.du) {

            int sum = 0;

            if (this.vectf2[i] > B.vectf2[j]) {

                C.vectf2[k] = this.vectf2[i];
                C.vectf2[k - 1] = this.vectf2[i - 1];
                i += 2;
                k += 2;

            } else if (B.vectf2[j] > this.vectf2[i]) {

                C.vectf2[k] = B.vectf2[j];
                C.vectf2[k - 1] = -1 * B.vectf2[j - 1];
                j += 2;
                k += 2;

            } else {

                sum = this.vectf2[i - 1] - B.vectf2[j-1];

                if (sum != 0) {

                    C.vectf2[k - 1] = sum;
                    C.vectf2[k] = this.vectf2[i];
                }
                i += 2;
                j += 2;
                k += 2;
            }
        }
        C.vectf2[0] = (k - 1) / 2;
        if(C.vectf2[1]!=0){
        C.Ajustar();
        C.MostrarPolinomio();
        }else {
        JOptionPane.showMessageDialog(null, "0");
        }
    }

    public void InsertarM(PolinomioF2 B) {

        int aux = B.getVectf2(2), i = 2;
        boolean a = true;

        while (i <= this.getDu() && a) {

            if (this.getVectf2(i) == aux) {

                this.setVectf2(i - 1, getVectf2(i - 1) + B.getVectf2(1));
                a = !a;

            } else {

                i += 2;
            }
        }
        if (a) {

            aux = getDu();
            RedimensionarAgrandar();
            setVectf2(aux + 1, B.getVectf2(1));
            setVectf2(aux + 2, B.getVectf2(2));
            this.Ordenar();
        }
    }

    public void Ordenar() {

        int auxc = 0, auxe = 0;

        for (int i = 2; i <= du; i += 2) {

            for (int j = i + 2; j <= du; j += 2) {
                auxe = vectf2[i];
                auxc = vectf2[i - 1];
                if (vectf2[i] < vectf2[j]) {

                    vectf2[i] = vectf2[j];
                    vectf2[i - 1] = vectf2[j - 1];
                    vectf2[j] = auxe;
                    vectf2[j - 1] = auxc;
                }
            }
        }

    }

    public void Evaluar(int x) {

        int resultado = 0;

        for (int i = 1; i <= du; i += 2) {

            int exp = vectf2[i + 1];
            resultado = (int) (resultado + (vectf2[i] * Math.pow(x, exp)));
        }
        JOptionPane.showMessageDialog(null, "El resultado del polinomio evaluando con "
                + x
                + " es = "
                + resultado);
    }

    public void MostrarPolinomio() {

        StringBuilder s = new StringBuilder();

        for (int i = 1; i <= du; i += 2) {

            int coefficient = vectf2[i];

            if (coefficient != 0) {

                if (coefficient > 0) {

                    if (i != 1) {

                        s.append("+");
                    }
                }

                if (vectf2[i + 1] > 1) {

                    if (coefficient != 1 && coefficient != -1) {

                        s.append(coefficient);

                    } else if (coefficient == -1) {

                        s.append("-");
                    }

                    s.append("x^").append(vectf2[i + 1]);

                } else if (vectf2[i + 1] == 1) {

                    if (coefficient != 1 && coefficient != -1) {

                        s.append(coefficient);

                    } else if (coefficient == -1) {

                        s.append("-");
                    }
                    s.append("x");

                } else if (vectf2[i + 1] == 0) {

                    s.append(coefficient);
                }
            }
        }
        JOptionPane.showMessageDialog(null, s.toString());
    }

    public void Multiplicar(PolinomioF2 B) {

        int expn = 0, expm = 0, expc = 0, k = 2;
        PolinomioF2 C = new PolinomioF2((this.vectf2[0] + B.vectf2[0]) * 2);
        C.vectf2[0] = (this.vectf2[0] + B.vectf2[0]) * 2;

        for (int j = 2; j <= B.du; j += 2) {

            for (int i = 2; i <= this.du; i += 2) {

                expn = this.vectf2[i];
                expm = B.vectf2[j];
                expc = expm + expn;

                boolean termExists = false;

                for (int l = 2; l <= k+2; l += 2) {

                    if (C.vectf2[l] == expc) {
                        C.vectf2[l - 1] += this.vectf2[i - 1] * B.vectf2[j - 1];
                        termExists = true;
                        
                    }
                }

                if (!termExists) {

                    C.vectf2[k - 1] = this.vectf2[i - 1] * B.vectf2[j - 1];
                    C.vectf2[k] = expc;
                    k += 2;
                }
            }
        }
        C.setVectf2(0, (k / 2));
        C.setDu(k);
        C.Redimensionar();
        C.MostrarF2();
        C.MostrarPolinomio();
    }

    public void EliminarF2(PolinomioF2 E) {

        boolean swt = false;
        for (int i = 2; i < vectf2.length; i += 2) {

            if (this.getVectf2(i) == E.getVectf2(2) && this.getVectf2(i - 1) == E.getVectf2(1)) {

                this.setVectf2(i, 0);
                this.setVectf2(i - 1, 0);
                swt = true;
                EliminarTrue(swt);

                if (this.getVectf2(1) == 0 && this.getVectf2(2) == 0) {
                    Ajustar();
                }
            }
        }
    }

    public void EliminarTrue(boolean sw) {
        if (sw = true) {
            JOptionPane.showMessageDialog(null, "Eliminado Con Exito");

        } else {
            JOptionPane.showMessageDialog(null, "No Se EncontrÃ³ El Monomio Ingresado");
        }
    }

    public int[] getVectf2() {
        return vectf2;
    }

    public int getVectf2(int i) {
        return vectf2[i];
    }

    public void setVectf2(int[] vectf2) {
        this.vectf2 = vectf2;
    }

    public void setVectf2(int i, int dato) {
        this.vectf2[i] = dato;
    }

    public int getDu() {
        return du;
    }

    public void setDu(int du) {
        this.du = du;
    }
    
    public boolean Vacio() {
        return this == null ? true : false;
    }
}
