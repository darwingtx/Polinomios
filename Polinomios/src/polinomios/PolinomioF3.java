package polinomios;

import javax.swing.JOptionPane;

public class PolinomioF3 {

    private Nodo Punta;

    public PolinomioF3() {
        Punta = null;
    }

    public void InsertarFinal(int coe, int exp) {

        Nodo x = new Nodo(coe, exp);

        if (Punta != null) {

            Nodo p = Punta;

            while (p.getLiga() != null) {

                p = p.getLiga();
            }
            p.setLiga(x);

        } else {

            Punta = x;
        }
    }

    public void ConvertF3(String[] Vs) {

        int i = 1, coe = 0, exp = 0;

        while (i < Vs.length && Vs[i] != null) {

            coe = Integer.parseInt(Vs[i - 1]);
            exp = Integer.parseInt(Vs[i]);
            i += 2;
            InsertarFinal(coe, exp);
        }
    }

    public void MostrarForma() {

        if (Punta != null) {

            String s = "";
            Nodo p = Punta;

            do {

                s = s + " | " + p.getCoe() + " | " + p.getExp() + " | " + "--->";
                p = p.getLiga();

            } while (p != null);

            JOptionPane.showMessageDialog(null, s);

        } else {

            JOptionPane.showMessageDialog(null, "No hay polinomio");
        }
    }

    public void Eliminar(Nodo b) {

        Nodo p = Punta, s = p;
        boolean a = true;

        while (p != null && a) {

            if (p.getCoe() == b.getCoe() && p.getExp() == b.getExp()) {

                a = !a;

            } else {

                p = p.getLiga();
            }
        }
        while (s != null && !a) {

            if (s.getLiga() == p) {

                a = !a;

            } else {

                s = s.getLiga();
            }
        }
        if (p != Punta) {

            s.setLiga(p.getLiga());

        } else {

            Punta = Punta.getLiga();
        }
    }

    public void Evaluar(int x) {

        int resultado = 0;
        Nodo p = Punta;

        while (p != null) {

            resultado = (int) (resultado + (p.getCoe() * Math.pow(x, p.getExp())));
            p = p.getLiga();
        }
        JOptionPane.showMessageDialog(null, "El resultado del polinomio evaluando con "
                  + x
                  + " es = "
                  + resultado);
    }

    
    public void InsertarOrdenado(int coe, int exp) {

        Nodo x = new Nodo(coe, exp);
        boolean a = true;

        if (Punta != null) {
            Nodo p = Punta;

            if (Punta.getExp() == x.getExp()) {

                p.setCoe(p.getCoe() + x.getCoe());

            } else if (Punta.getExp() < x.getExp()) {

                this.InsertarInicio(coe, exp);

            } else {

                Nodo q = null;

                while (p != null && a) {

                    if (p.getExp() < x.getExp()) {

                        x.setLiga(p);

                        if (q != null) {

                            q.setLiga(x);

                        } else {

                            Punta = x;
                        }
                        a = false;

                    } else if (p.getExp() == x.getExp()) {

                        p.setCoe(p.getCoe() + x.getCoe());
                        a = false;

                    } else {

                        q = p;
                        p = p.getLiga();
                    }
                }
                if (a) {

                    InsertarFinal(coe, exp);
                }
            }

        } else {

            Punta = x;
        }
    }

    public void MostrarP() {

        StringBuilder s = new StringBuilder();
        Nodo p = Punta;

        while (p != null) {

            int exp = p.getExp();
            int coefficient = p.getCoe();

            if (coefficient != 0) {

                if ((p != Punta) && coefficient > 0) {

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
            p = p.getLiga();
        }
        JOptionPane.showMessageDialog(null, s.toString());
    }

    public void Sumar(PolinomioF3 B) {

        PolinomioF3 C = new PolinomioF3();

        Nodo p = this.Punta;
        Nodo x = B.Punta;

        while (p != null && x != null) {

            if (p.getExp() == x.getExp()) {

                int coeSum = p.getCoe() + x.getCoe();

                if (coeSum != 0) {

                    C.InsertarFinal(coeSum, p.getExp());
                }
                p = p.getLiga();
                x = x.getLiga();

            } else if (p.getExp() > x.getExp()) {

                C.InsertarFinal(p.getCoe(), p.getExp());
                p = p.getLiga();

            } else {

                C.InsertarFinal(x.getCoe(), x.getExp());
                x = x.getLiga();
            }
        }

        while (p != null) {

            C.InsertarFinal(p.getCoe(), p.getExp());
            p = p.getLiga();
        }

        while (x != null) {

            C.InsertarFinal(x.getCoe(), x.getExp());
            x = x.getLiga();
        }
        C.MostrarForma();
        C.MostrarP();
    }
    
    public void Restar(PolinomioF3 B) {

        PolinomioF3 C = new PolinomioF3();

        Nodo p = this.Punta;
        Nodo x = B.Punta;

        while (p != null && x != null) {

            if (p.getExp() == x.getExp()) {

                int coeSum = p.getCoe() - x.getCoe();

                if (coeSum != 0) {

                    C.InsertarFinal(coeSum, p.getExp());
                }
                p = p.getLiga();
                x = x.getLiga();

            } else if (p.getExp() > x.getExp()) {

                C.InsertarFinal(p.getCoe(), p.getExp());
                p = p.getLiga();

            } else {

                C.InsertarFinal(-1 * x.getCoe(), x.getExp());
                x = x.getLiga();
            }
        }

        while (p != null) {

            C.InsertarFinal(p.getCoe(), p.getExp());
            p = p.getLiga();
        }

        while (x != null) {

            C.InsertarFinal(x.getCoe(), x.getExp());
            x = x.getLiga();
        }
        C.MostrarForma();
        C.MostrarP();
    }

    public void InsertarInicio(int coe, int exp) {

        Nodo x = new Nodo(coe, exp);

        if (Punta == null) {

            Punta = x;

        } else {

            x.setLiga(Punta);
        }
        Punta = x;
    }

    public void Multiplicar(PolinomioF3 B) {

        PolinomioF3 C = new PolinomioF3();
        Nodo p, x;
        p = this.Punta;

        while (p != null) {

            x = B.Punta;

            while (x != null) {

                int coe = p.getCoe() * x.getCoe();
                int exp = p.getExp() + x.getExp();

                C.InsertarOrdenado(coe, exp);
                x = x.getLiga();

            }
            p = p.getLiga();
        }
        C.MostrarForma();
        C.MostrarP();
    }

    public Nodo getPunta() {
        return Punta;
    }

    public void setPunta(Nodo Punta) {
        this.Punta = Punta;
    }
    
    
}