/*     */ package jparanoia.shared;
/*     */ 
/*     */ import javax.sound.sampled.AudioInputStream;
/*     */ import javax.sound.sampled.Clip;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SoundPlayer
/*     */   extends Thread
/*     */ {
/*     */   Clip currentClip;
/*     */   AudioInputStream audioStream;
/*     */   
/*     */   public SoundPlayer(Clip paramClip, AudioInputStream paramAudioInputStream)
/*     */   {
/* 129 */     this.currentClip = paramClip;
/* 130 */     this.audioStream = paramAudioInputStream;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void run()
/*     */   {
/* 138 */     this.currentClip.start();
/*     */     
/*     */ 
/*     */ 
/* 142 */     while (this.currentClip.isRunning())
/*     */     {
/*     */       try {
/* 145 */         sleep(99L);
/*     */       }
/*     */       catch (Exception localException) {}
/* 148 */       localException.printStackTrace();
/*     */     }
/* 150 */     this.currentClip.drain();
/* 151 */     this.currentClip.stop();
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\SoundPlayer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */