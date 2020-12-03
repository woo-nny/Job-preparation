
public class DailyVO {
   
   private int sum_study;
   private int sum_rest;
   private int sum_sleep;
   private int sum_eat;
   private int sum_etc;
   
   public DailyVO(int sum_study, int sum_rest, int sum_sleep, int sum_eat, int sum_etc) {
      this.sum_study = sum_study;
      this.sum_rest = sum_rest;
      this.sum_sleep = sum_sleep;
      this.sum_eat = sum_eat;
      this.sum_etc = sum_etc;
   }
   public int getSum_study() {
      return sum_study;
   }
   public void setSum_study(int sum_study) {
      this.sum_study = sum_study;
   }
   public int getSum_rest() {
      return sum_rest;
   }
   public void setSum_rest(int sum_rest) {
      this.sum_rest = sum_rest;
   }
   public int getSum_sleep() {
      return sum_sleep;
   }
   public void setSum_sleep(int sum_sleep) {
      this.sum_sleep = sum_sleep;
   }
   public int getSum_eat() {
      return sum_eat;
   }
   public void setSum_eat(int sum_eat) {
      this.sum_eat = sum_eat;
   }
   public int getSum_etc() {
      return sum_etc;
   }
   public void setSum_etc(int sum_etc) {
      this.sum_etc = sum_etc;
   }
   @Override
   public String toString() {
      return "DailyVO [sum_study=" + sum_study + ", sum_rest=" + sum_rest + ", sum_sleep=" + sum_sleep + ", sum_eat="
            + sum_eat + ", sum_etc=" + sum_etc + "]";
   }
   
   
   
}
