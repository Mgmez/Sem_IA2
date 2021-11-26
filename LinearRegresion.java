package examples.h2;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
public class LinearRegresion extends Agent{
    protected void setup(){
        addBehaviour(new Regresion());
    }
    private class Regresion extends OneShotBehaviour{
        //Parametros para la prediccion
        ///Data set
        private double X[] = {1,2,3,4,5,6,7,8,9};//{23, 26, 30, 34, 43, 48, 52, 57, 58};
        private double Y[] = {2,4,6,8,10,12,14,16,18};//{651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};
        private double a = 0.0;
        private void getAParametrer(double dataX, double dataY, double b, int n) {
            this.a = ((dataY / n) - (b * (dataX / n)));

        }

        private double getBParametrer(double[] dataX, double[] dataY) {
            double sumXi = 0.0;
            double sumYi = 0.0;
            double sumXY = 0.0;
            double sumX2 = 0.0;
            double b = 0.0;
            for (int i = 0; i < dataX.length; i++) {
                sumXi += dataX[i];
                sumYi += dataY[i];
                sumXY += dataX[i] * dataY[i];
                sumX2 += dataX[i] * dataX[i];
            }

            //Calculo del parametro b
            b = ((sumXY - ((sumXi * sumYi) / dataX.length)) / (sumX2 - ((sumXi * sumXi) / dataX.length)));
            //Calculamos el parametro a
            this.getAParametrer(sumXi, sumYi, b, dataX.length);
            return b;

        }

        public String regresionLineal() {
            //Obtenemos parametro a
            //int ciclo = 10;
            double b = this.getBParametrer(this.X, this.Y);
            String regLineal = "Valor de a =" + a + "Valor de b=" + b + "\n";
            for (int i = 0; i < X.length; i++) {
                regLineal += "Cuando X es " + X[i] + " Y es " + (a + (b * X[i])) + "\n";
            }
            regLineal += "Cuando X es " + 60 + " Y es " + (a + (b * 60)) + "\n";
            regLineal += "Cuando X es " + 70 + " Y es " + (a + (b * 70)) + "\n";
            regLineal += "Cuando X es " + 80 + " Y es " + (a + (b * 80)) + "\n";
            return regLineal;

        }
        public void action(){
            System.out.print(this.regresionLineal());
        }
        public int onEnd(){
            myAgent.doDelete();
            return super.onEnd();
        }
    }
}
