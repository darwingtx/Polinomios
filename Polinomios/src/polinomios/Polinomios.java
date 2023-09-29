package polinomios;

import javax.swing.JOptionPane;

public class Polinomios {

    public static void main(String[] args) {

        int op = 0;

        do {
            op = Integer.parseInt(JOptionPane.showInputDialog("""
                    ---¿Que forma desea usar?---
                    1. Forma 1
                    2. Forma 2
                    3. Forma 3
                    0. Salir
                    """));

            switch (op) {
                case 1:
                    MenuF1();
                    break;

                case 2:
                    MenuF2();
                    break;

                case 3:
                    MenuF3();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Adios!!!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción Incorrecta");
                    break;
            }
        } while (op != 0);

    }

    public static String[] Ingresar() {
        String cadena = JOptionPane.showInputDialog("Ingrese el Polinomio");
        String dato = "";
        int j = 0;// iterador para el vector string

        char Vc[] = cadena.toCharArray();// convierte la cadena a un vector por posiciones
        String Vs[] = new String[Vc.length + 2];// crear vector de tipo String para el polinomio sin x y ^

        for (int i = 0; i < Vc.length; i++) {

            System.out.print("[" + Vc[i] + "]");

        }

        for (int i = 0; i < Vc.length; i++) {

            if (Character.isDigit(Vc[i]) || Vc[i] == '-') {

                dato = dato + Character.toString(Vc[i]);// concatena si es un numero o empieza con -

            } else {

                if (Vc[i] == 'x') {
                    if (i > 0 && Character.isDigit(Vc[i - 1])) {
                        Vs[j] = dato;
                        dato = "";
                        j++;
                    } else {
                        if (i > 0 && Vc[i - 1] == '-') {
                            Vs[j] = "-1";
                            dato = "";
                            j++;
                        } else if (i == 0 || Vc[i - 1] == '+') {
                            Vs[j] = "1";
                            dato = "";
                            j++;
                        }
                    }

                    if (i + 2 > Vc.length || (i + 1 != Vc.length && Vc[i + 1] != '^')) {
                        Vs[j] = "1";
                        j++;
                    }
                }

                if (Vc[i] == '^') {
                    Vs[j] = Character.toString(Vc[i + 1]);
                    j++;
                    i++;
                }
            }
            // termino independiete
            if (i + 1 != Vc.length
                    && ((Vc[i + 1] == '-' || Vc[i + 1] == '+' && Vc[i + 1] != 'x') && !dato.equals(""))) {
                Vs[j] = dato;
                Vs[j + 1] = "0";
                j += 2;
                dato = "";
            }

            if (!dato.equals("") && i + 2 > Vc.length && Character.isDigit(Vc[i])) {
                Vs[j] = dato;
                Vs[j + 1] = "0";
            }

        }

        // organizar el vector string
        if (Vs[2] != null) {
            String auxc = "", auxe = "";
            for (int i = 1; i < Vs.length && Vs[i] != null; i += 2) {
                    
                for (int k = i + 2;  Vs[k] != null && k < Vs.length ; k += 2) {
                    auxc = Vs[i - 1];
                    auxe = Vs[i];
                    if (Integer.parseInt(Vs[k]) !=0 && (Integer.parseInt(Vs[i]) < Integer.parseInt(Vs[k]))) {
                        Vs[i] = Vs[k];
                        Vs[i - 1] = Vs[k - 1];
                        Vs[k] = auxe;
                        Vs[k - 1] = auxc;
                        
                    }
                }

            }
        }

        System.out.println(" ");
        for (int i = 0; i <= Vc.length; i++) {

            System.out.print("[" + Vs[i] + "]");

        }
        return Vs;
    }

    
    public static int Grado(String Vs[]) {
        int aux = 0;
        for (int i = 1; i < Vs.length && Vs[i] != null; i += 2) {
            if (aux < Integer.parseInt(Vs[i])) {
                aux = Integer.parseInt(Vs[i]);
            }
        }
        return aux;
    }

    public static int Terminos(String Vs[]) {
        int term = 0;
        for (int i = 1; i < Vs.length && Vs[i] != null; i += 2) {
            term++;
        }
        return term;
    }

    public static void MenuF1() {
        int op = 0;
        PolinomioF1 A = null;
        String vc[];

        do {
            op = menuOp();

            switch (op) {
                case 1:
                    String Vs[] = Ingresar();
                    A = new PolinomioF1(Grado(Vs) + 1);
                    A.ConvertF1(Vs);
                    break;

                case 2:
                    if (A != null) {
                        A.MostrarF1();
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay polinomio");
                    }
                    break;

                case 3:
                    if (A != null) {
                        A.MostrarP();
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay polinomio");
                    }
                    break;

                case 4:

                    do {
                        vc = Ingresar();
                        if (vc[2] != null) {
                            JOptionPane.showInputDialog("Ingrese solo un monomio");
                        }
                    } while (vc[2] != null);

                    PolinomioF1 c = new PolinomioF1((Integer.parseInt(vc[1]) + 1));

                    c.ConvertF1(vc);
                    A.InsertarM(c);
                    break;
                case 5:
                    do {
                        vc = Ingresar();
                        if (vc[2] != null) {
                            JOptionPane.showInputDialog("Ingrese el monomio a eliminar");
                        }
                    } while (vc[2] != null);

                    PolinomioF1 d = new PolinomioF1((Integer.parseInt(vc[1]) + 1));
                    d.ConvertF1(vc);
                    A.Eliminar(d);

                    break;
                case 6:
                    if (A != null) {
                        int x = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor de x"));
                        A.Evaluar(x);
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay polinomio");
                    }
                    break;

                case 7:
                    int op2 = 0;
                    PolinomioF1 B = null;
                    String VsB[] = null;
                    do {
                        op2 = menuOperaciones();
                        switch (op2) {
                            case 1:
                                if (A != null) {
                                    A.MostrarP();
                                    JOptionPane.showMessageDialog(null, "+");
                                    VsB = Ingresar();
                                    B = new PolinomioF1(Grado(VsB) + 1);
                                    B.ConvertF1(VsB);
                                    A.Sumar(B);
                                } else {
                                    JOptionPane.showMessageDialog(null, "No hay polinomio a operar");
                                }
                                break;

                            case 2:
                                if (A != null) {
                                    A.MostrarP();
                                    JOptionPane.showMessageDialog(null, "+");
                                    VsB = Ingresar();
                                    B = new PolinomioF1(Grado(VsB) + 1);
                                    B.ConvertF1(VsB);
                                    A.Restar(B);
                                } else {
                                    JOptionPane.showMessageDialog(null, "No hay polinomio a operar");
                                }
                                break;

                            case 3:
                                if (A != null) {

                                    A.MostrarP();
                                    JOptionPane.showMessageDialog(null, "+");
                                    VsB = Ingresar();
                                    B = new PolinomioF1(Grado(VsB) + 1);
                                    B.ConvertF1(VsB);
                                    A.Multiplicar(B);
                                } else {
                                    JOptionPane.showMessageDialog(null, "No hay polinomio a operar");
                                }
                                break;
                            case 0:
                                JOptionPane.showMessageDialog(null, "Has salido del menu operaciones");
                                break;

                            default:
                                JOptionPane.showMessageDialog(null, "Error en la opcion, intente de nuevo");
                                break;
                        }
                    } while (op2 != 0);
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Has salido al menu de formas");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción Incorrecta");
                    break;
            }
        } while (op != 0);

    }

    public static void MenuF2() {
        int op = 0;
        PolinomioF2 A = null;
        String vc[];

        do {
            op = menuOp();

            switch (op) {
                case 1:
                    String Vs[] = Ingresar();
                    A = new PolinomioF2(Terminos(Vs) * 2);// 4
                    A.ConvertF2(Vs);
                    break;

                case 2:
                    if (A != null) {
                        A.MostrarF2();
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay polinomio");
                    }
                    break;

                case 3:
                    if (A != null) {
                        A.MostrarPolinomio();
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay polinomio");
                    }
                    break;

                case 4:

                    do {
                        vc = Ingresar();
                        if (vc[2] != null) {
                            JOptionPane.showInputDialog("Ingrese solo un monomio");
                        }
                    } while (vc[2] != null);

                    PolinomioF2 c = new PolinomioF2(Terminos(vc) * 2 + 1);

                    c.ConvertF2(vc);
                    A.InsertarM(c);
                    break;
                case 5:
                    do {
                        vc = Ingresar();
                        if (vc[2] != null) {
                            JOptionPane.showInputDialog("Ingrese solo un monomio");
                        }
                    } while (vc[2] != null);

                    PolinomioF2 e = new PolinomioF2(Terminos(vc) * 2 + 1);

                    e.ConvertF2(vc);
                    A.EliminarF2(e);
                    break;
                case 6:
                    int x = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor de x"));
                    A.EvaluarF2(x);
                    break;
                case 7:
                    int op2 = 0;
                    PolinomioF2 B = null;
                    String VsB[] = null;
                    do {
                        op2 = menuOperaciones();
                        switch (op2) {
                            case 1:
                                if (A != null) {
                                    A.MostrarPolinomio();
                                    JOptionPane.showMessageDialog(null, "+");
                                    VsB = Ingresar();
                                    B = new PolinomioF2(Terminos(VsB) * 2);
                                    B.ConvertF2(VsB);
                                    A.SumarF2(B);

                                } else {
                                    JOptionPane.showMessageDialog(null, "No hay polinomio a operar");
                                }
                                break;

                            case 2:
                                if (A != null) {
                                    A.MostrarPolinomio();
                                    JOptionPane.showMessageDialog(null, "+");
                                    VsB = Ingresar();
                                    B = new PolinomioF2(Terminos(VsB) * 2);
                                    B.ConvertF2(VsB);
                                    A.RestarF2(B);

                                } else {
                                    JOptionPane.showMessageDialog(null, "No hay polinomio a operar");
                                }
                                break;

                            case 3:
                                if (A != null) {
                                    A.MostrarPolinomio();
                                    JOptionPane.showMessageDialog(null, "+");
                                    VsB = Ingresar();
                                    B = new PolinomioF2(Terminos(VsB) * 2);
                                    B.ConvertF2(VsB);
                                    A.Multiplicar(B);

                                } else {
                                    JOptionPane.showMessageDialog(null, "No hay polinomio a operar");
                                }
                                break;

                            case 0:
                                JOptionPane.showMessageDialog(null, "Has salido del menu operaciones");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Error en la opcion, intente de nuevo");
                                break;
                        }
                    } while (op2 != 0);
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Has salido al menu de formas");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción Incorrecta");
                    break;
            }
        } while (op != 0);

    }

    public static void MenuF3() {
        int op = 0;
        PolinomioF3 A = null;
        String vc[];

        do {
            op = menuOp();

            switch (op) {
                case 1:
                    String Vs[] = Ingresar();
                    A = new PolinomioF3();
                    A.ConvertF3(Vs);
                    break;

                case 2:
                    if (A != null) {

                        A.MostrarForma();
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay polinomio");
                    }
                    break;

                case 3:
                    if (A != null) {
                        A.MostrarP();

                    } else {
                        JOptionPane.showMessageDialog(null, "No hay polinomio");
                    }
                    break;

                case 4:

                    do {
                        vc = Ingresar();
                        if (vc[2] != null) {
                            JOptionPane.showInputDialog("Ingrese solo un monomio");
                        }
                    } while (vc[2] != null);

                    PolinomioF3 c = new PolinomioF3();
                    c.ConvertF3(vc);
                    A.InsertarOrdenado(c.getPunta().getCoe(), c.getPunta().getExp());

                    break;
                case 5:
                    Vs = Ingresar();
                    PolinomioF3 b = new PolinomioF3();
                    b.ConvertF3(Vs);
                    A.Eliminar(b.getPunta());
                    A.MostrarP();
                    ;
                    break;
                case 6:
                    int x = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor de x"));
                    A.Evaluar(x);
                    break;
                case 7:
                    int op2 = 0;
                    PolinomioF3 B = new PolinomioF3();
                    String VsB[] = null;
                    do {
                        op2 = menuOperaciones();
                        switch (op2) {
                            case 1:
                                if (A == null) {
                                    A.MostrarP();
                                    JOptionPane.showMessageDialog(null, "+");
                                    VsB = Ingresar();
                                    B.ConvertF3(VsB);
                                    A.Sumar(B);
                                } else {
                                    JOptionPane.showMessageDialog(null, "No hay polinomio a operar");
                                }
                                break;
                            case 2:
                            if (A != null) {
                                   
                                A.MostrarP();
                                JOptionPane.showMessageDialog(null, "-");
                                VsB = Ingresar();
                                B = new PolinomioF3();
                                B.ConvertF3(VsB);
                                A.Restar(B);

                            } else {
                                JOptionPane.showMessageDialog(null, "No hay polinomio a operar");
                            }
                            break;
                              

                            case 3:
                            if (A != null) {
                                   
                                A.MostrarP();
                                JOptionPane.showMessageDialog(null, "*");
                                VsB = Ingresar();
                               // B = new PolinomioF3();
                               // B.ConvertF3(VsB);
                              //  A.Multiplicar(B);

                            } else {
                                JOptionPane.showMessageDialog(null, "No hay polinomio a operar");
                            }
                            break;

                            case 0:
                                JOptionPane.showMessageDialog(null, "Has salido del menu operaciones");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Error en la opcion, intente de nuevo");
                                break;
                        }
                    } while (op2 != 0);
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Has salido al menu de formas");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción Incorrecta");
                    break;
            }
        } while (op != 0);

    }

    public static int menuOperaciones() {
        return Integer.parseInt(JOptionPane.showInputDialog("***Menu Polinomio***\n\n"
                + "1) Sumar\n"
                + "2) Restar\n"
                + "3) Multiplicar\n"
                + "0) Salir"));
    }

    public static int menuOp() {
        int op = Integer.parseInt(JOptionPane.showInputDialog("***Menu Polinomio***\n\n"
                + "1) Ingresar Polinomio\n"
                + "2) Mostrar Forma\n"
                + "3) Mostrar Polinomio\n"
                + "4) Insertar monomio\n"
                + "5) eliminar monomio\n"
                + "6) Evaluar\n"
                + "7) Operaciones\n"
                + "0) Salir"));

        return op;
    }
}
