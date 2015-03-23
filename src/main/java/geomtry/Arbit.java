package geomtry;

import java.io.*;

/**
 * Created by kuldeep on 10/20/14.
 */
public class Arbit {

    public static class ArbitObj implements Externalizable{
        private static final long serialVersionUID = 1;

        String s1;
        long l2;
        String s3;
        byte[] ax;

        public ArbitObj(String s1, long l2, String s3, byte[] ax) {
            this.s1 = s1;
            this.l2 = l2;
            this.s3 = s3;
            this.ax = ax;
        }

        public ArbitObj() {

        }
        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(s1);
            out.writeLong(l2);
            out.writeObject(s3);
            out.writeObject(ax);
        }


        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            s1 = (String) in.readObject();
            l2 =  in.readLong();
            s3 = (String) in.readObject();
            ax = (byte[]) in.readObject();
        }

        @Override
        public String toString() {
            return "["+
                    s1 + "  "  + l2 + " " + s3 + " " + new String(ax) +
                    "]";
        }
    }
    public static void main(String[] args) {
        try {

            FileOutputStream fos = new FileOutputStream("/home/kuldeep/Downloads/arbit1.ts");
            ObjectOutputStream os = new ObjectOutputStream(fos);
            ArbitObj ob1 = new ArbitObj("Hello",42343,"World", "Peek".getBytes()) ;
            ArbitObj ob2 = new ArbitObj("Hello1",42343,"World", "Peek".getBytes()) ;
            ArbitObj ob3 = new ArbitObj("Hello2",42343,"World", "Peek".getBytes()) ;
            ArbitObj ob4 = new ArbitObj("Hello3",42343,"World", "Peek".getBytes()) ;
            ArbitObj ob5 = new ArbitObj("Hello4",42343,"World", "Peek".getBytes()) ;

            os.writeObject(ob1);
            os.writeObject(ob2);
            os.writeObject(ob3);
            os.writeObject(ob4);
            os.writeObject(ob5);

//          fos.close();
            FileInputStream fis = new FileInputStream("/home/kuldeep/Downloads/arbit1.ts");
             ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.println(ois.readObject());
            System.out.println(ois.readObject());
            System.out.println(ois.readObject());
            System.out.println(ois.readObject());
            System.out.println(ois.readObject());
//            System.out.println(ois.readObject());
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
