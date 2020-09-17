package bug_catch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class ThreadFinishFlagEx extends JFrame{
    ThreadFinishFlagEx(){
        this.setTitle("ThreadFinishFlag 예제");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container c = this.getContentPane();
        RandomThread rt = new RandomThread(c);//스레드 생성
        c.addMouseListener(new MouseListener(){//마우스 리스너 등록
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                rt.finish();//스레드 종료 명령
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
            
        });
        this.setLocationRelativeTo(this.getParent());
        this.setSize(300,200);
        this.setVisible(true);
        rt.start();//스레드 시작
    }
    class RandomThread extends Thread{
        Container cc;
        boolean flag =false;//스레드의 종료 명령을 위한 플래그
        RandomThread(Container cc){
            this.cc= cc;
        }
        
        void finish(){flag=true;}//스레드 종료를 위한 함수
        
        public void run(){
            while(true){
                int x=((int)(Math.random()*cc.getWidth()));
                int y=((int)(Math.random()*cc.getHeight()));
                JLabel la = new JLabel("Java");//새 레이블 생성
                la.setBounds(x, y, 80, 30);//위치와 크기를 지정
                cc.add(la);//레이블 추가
                cc.repaint();//다시 그려 추가된 레이블 보이기
                try{
                    sleep(300);//0.3초동안 잠을 잔다.
                    if(flag==true){
                        cc.removeAll();//모든 레이블 제거
                        la=new JLabel("Finish");
                        la.setBounds(x, y, 80, 30);//위치와 크기 지정
                        la.setForeground(Color.red);//글자색 빨간색으로 지정
                        cc.add(la);//레이블 추가
                        cc.repaint();//Finish 레이블 보이기
                        return;//스레드 종료
                    }
                }
                catch(Exception e){return;}
            }
        }
    }
    public static void main(String[] args) {
        new ThreadFinishFlagEx();
    }
}
