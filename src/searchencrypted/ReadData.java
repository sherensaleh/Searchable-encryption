/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package searchencrypted;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import javax.lang.model.SourceVersion.latest;
//import net.didion.jwnl.JWNL;
//import net.didion.jwnl.JWNLException;
//import net.didion.jwnl.data.*;
//import net.didion.jwnl.data.list.PointerTargetNodeList;
//import net.didion.jwnl.dictionary.Dictionary;

public class ReadData implements Serializable {

    private static final long serialVersionUID = 1L;
    double[] e = new double[100];
    double[] k = new double[100];
    int[] sh;
    static int[] max2 = new int[2];
    static int max = 0;
    String ffile = "";
    String lineout = "";
    static BloomFilter bf = new BloomFilter(100, 5);
    LSH lsh;
    int id = 0;
    List<be.tarsos.lsh.Vector> bitset2;
    List<be.tarsos.lsh.Vector> bitset23;
    BloomFilterIndex st;
    List<BloomFilter> bfList = new ArrayList<BloomFilter>();
    Hashtable<String, List<double[]>> hasssh = new Hashtable<String, List<double[]>>();
    double[] c;
    long startTime3 = 0;
    double end16 = 0.0;
    Hashtable<Integer, List<String>> tablee = new Hashtable<Integer, List<String>>();
    Hashtable<Integer, List<String>> hashTTTT = new Hashtable<Integer, List<String>>();
    double createTreeTime = 0.0;
    double startTime5 = 0.0;
    double endLinearSearchTime = 0.0;
    java.util.Vector<Integer> s;
    double[][] value1;
    double[][] value2;
    double[][] a2;
    double[][] b2;
    double encryptedBFListInTime = 0.0;
    long startTime10 = 0;
    double end = 0.0;
    long startTime9 = 0;
    double createTreeTime10 = 0.0;
    long startTime12;
    double end12 = 0.0;
    double end13 = 0.0;
    double end19 = 0.0;
    double end199 = 0.0;
    double end17 = 0.0;
    EuclideanDistance distanc = new EuclideanDistance();
    static Integer[] result;
    EuclidianHashFamily eHF = new EuclidianHashFamily(25, 675);
    HashTable ble = new HashTable(bf.k, new EuclidianHashFamily(25, 675));
    Random r;
    Integer g1;
    double m5;

    public ReadData() {

    }

    ArrayList<String> readWordsFromFile(String file) {
        Scanner s = null;
        ArrayList<String> list = new ArrayList<String>();

        try {
            s = new Scanner(new File(file));
            while (s.hasNext()) {
                list.add(s.next());
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadData.class.getName()).log(Level.SEVERE, null, ex);
        }

        s.close();
        return list;
    }

    public void generateMatrix() {
        s = new Vector<Integer>(100);
        for (int i = 0; i < 100; i++) {
            int random0Or1 = (int) Math.round(Math.random());
            s.add(random0Or1);
        }
        value1 = new double[100][100];
        value2 = new double[100][100];

        Random ra = new Random();

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {

                double g = ra.nextFloat();
                double h = ra.nextFloat();

                double m = (double) ((double) Math.round(g * 10) / 10.0);
                double n = (double) ((double) Math.round(h * 10) / 10.0);

                value1[i][j] = m;
                value2[i][j] = n;

            }
        }

    }
//

    public BloomFilterIndex createBloomFilterIndexObject() {

        startTime10 = System.nanoTime();
        st = new BloomFilterIndex(bfList, 2, true, new InsDelUpdateStatistics());
        createTreeTime = System.nanoTime();
        createTreeTime10 = createTreeTime - startTime10;
        createTreeTime10 /= 1000000000;

        return st;

    }

    void generateBitSet2(ArrayList<String> list) {
        bitset2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Vector<String> vec1 = new Vector<String>(20);

            vec1 = customSplit(list.get(i), 2);
            be.tarsos.lsh.Vector bitset1 = new be.tarsos.lsh.Vector(675);
            for (int n = 0; n < vec1.size(); n++) {
                int asci_of_a = (int) 'a';
                String l = vec1.get(n);
                char[] c = l.toCharArray();
                int first = ((int) c[0] - asci_of_a) * 26;
                int second = (int) c[1] - asci_of_a;
                int index = first + second;
                bitset1.set(index, (int) 1.0);
            }
            bitset2.add(bitset1);

        }
        for (int h = 0; h < bitset2.size(); h++) {
            be.tarsos.lsh.Vector vec = bitset2.get(h);
            for (be.tarsos.lsh.Vector bit : bitset2) {

                double dou = distanc.distance(vec, bit);
                if (dou == 4.0 || dou == 2.0) {

                    int ind = bitset2.indexOf(bit);
                    bit = vec;

                    bitset2.set(ind, bit);
                } else {
                    this.bitset2 = bitset2;
                }

            }
        }
    }

    void setOutLine(String file) throws FileNotFoundException, IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while ((line = in.readLine()) != null) {
            lineout += line + "\n";
        }
        in.close();
    }
    
        public void Read(String file, String fileName) throws FileNotFoundException, MalformedURLException, IOException {

        BloomFilter f = new BloomFilter(100, 5);
        f.setID(id++);
        f.setName(fileName);
        ArrayList<String> listt = new ArrayList();
        listt = readWordsFromFile(file);
        ffile = file;

        generateBitSet2(listt);

        lsh = new LSH(bitset2, eHF);
        Index x = lsh.buildIndex(bf.k, 1);
        List<int[]> hashResults = HashTable.listOfCombinedHash;
        for (int[] hashResult : hashResults) {
            for (int i = 0; i < hashResult.length; i++) {
                f.setBit(hashResult[i], true);
            }
        }
        bfList.add(f);

        setOutLine(file);

    }

    void prepareQuery(List<String> queries) {
        bf.clear();
        List<Integer> totalHash = new ArrayList<Integer>();
        for (String dataset : queries) {
            int[] sh = new int[5];
            Vector<String> query = new Vector<String>(20);
            for (int i = 0; i < dataset.length() - 1; i++) {
                query.add(dataset.substring(i, (i + 2 > dataset.length()) ? dataset.length() : i + 2));
            }

            be.tarsos.lsh.Vector bi = new be.tarsos.lsh.Vector(675);
            for (int n = 0; n < query.size(); n++) {
                int asci_of_a = (int) 'a';
                String ss = query.get(n);
                char[] c = ss.toCharArray();
                int first = ((int) c[0] - asci_of_a) * 26;
                int second = (int) c[1] - asci_of_a;
                int index = first + second;
                bi.set(index, (int) 1.0);
            }
           // for (int h = 0; h < bitset2.size(); h++) {
              //  be.tarsos.lsh.Vector vec = bitset2.get(h);
                for (be.tarsos.lsh.Vector bit : bitset2) {

                    double dou = distanc.distance(bi, bit);
                    if (dou == 4.0 || dou == 2.0) {

                        int ind = bitset2.indexOf(bit);
                        bi = bit;
                    }
                }
           // }

            sh = lsh.query(bi, 2);
            for (int l = 0; l < sh.length; l++) {
                if (!(totalHash.contains(sh[l]))) {
                    totalHash.add(sh[l]);
                }
            }
            result = totalHash.toArray(new Integer[totalHash.size()]);
            for (int i = 0; i < sh.length; i++) {
                bf.setBit(sh[i], true);
            }
        }
        encryptQuery(bf);
    }

    public void encryptQuery(BloomFilter q) {
        generateMatrix();

        Matrix matrix = new Matrix(100, 100);
        Matrix matrix1 = new Matrix(100, 100);
        matrix.setMat(value1);
        matrix1.setMat(value2);

        Matrix x = matrix.inverseMatrix();
        Matrix r = matrix1.inverseMatrix();
        double[][] a1 = x.getMat();
        double[][] b1 = r.getMat();

        Matrix matrix2 = new Matrix(100, 100);
        Matrix matrix3 = new Matrix(100, 100);

        matrix2.setMat(value1);
        matrix3.setMat(value2);

        a2 = matrix2.transpose();
        b2 = matrix3.transpose();
        Vector<Double> w = new Vector<Double>(100);
        Vector<Double> p = new Vector<Double>(100);
        Random r1 = new Random();
        Float g1 = r1.nextFloat();
        double m11 = (double) ((double) Math.round(g1 * 10) / 10.0);

        for (int i = 0; i < s.size(); i++) {
            if (s.get(i) == 0) {
                w.add((double) bf.getBitSet().getWord(i));
                p.add((double) bf.getBitSet().getWord(i));
            } else {

                w.add(0.5 * (bf.getBitSet().getWord(i)) + m11);
                p.add(0.5 * (bf.getBitSet().getWord(i)) + m11);
            }
        }
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                e[i] += (a1[i][j]) * (w.get(j));
                k[i] += (b1[i][j]) * (p.get(j));

            }
        }
    }

    void readQuery(List<String> queries) {
        prepareQuery(queries);
    }



    public void encryptFiles(List<BloomFilter> bfList1) {
        startTime3 = System.currentTimeMillis();
        Random raa = new Random();
        int g1 = raa.nextInt();
        double m1 = (double) ((double) Math.round(g1 * 10) / 10.0);
        for (BloomFilter bfList2 : bfList1) {
            List<double[]> res1 = new ArrayList<>();
            double[] e1 = new double[100];
            double[] k1 = new double[100];

            Vector<Double> j = new Vector<Double>(100);
            Vector<Double> h = new Vector<Double>(100);

            String bfName = bfList2.getName();
            for (int i = 0; i < s.size(); i++) {
                if (s.get(i) == 1) {
                    j.add((double) bfList2.getBitSet().getWord(i));

                    h.add((double) bfList2.getBitSet().getWord(i));
                } else {

                    j.add(0.5 * (bfList2.getBitSet().getWord(i)) + m1);
                    h.add(0.5 * (bfList2.getBitSet().getWord(i)) + m1);
                }
            }

            for (int u = 0; u < 100; u++) {
                for (int y = 0; y < 100; y++) {
                    e1[u] += a2[u][y] * j.get(y);
                    k1[u] += b2[u][y] * h.get(y);

                }
            }
            res1.add(e1);
            res1.add(k1);
            encryptedBFListInTime += System.currentTimeMillis() - startTime3;
            hasssh.put(bfName, res1);
        }

        encryptedBFListInTime /= 1000;
    }

    public BloomFilterIndex encryptTree(BloomFilterIndex tree) {
        r = new Random();
        g1 = r.nextInt();
        m5 = (double) ((double) Math.round(g1 * 10) / 10.0);
        startTime9 = System.nanoTime();
        for (int m = 0; m < tree.root.children.size(); m++) {

            BloomFilterIndex.BFINode<Integer> n = (BloomFilterIndex.BFINode<Integer>) tree.root.children.get(m);
            encrypt(n);
            end = System.nanoTime();
            end16 += end - startTime9;

        }

        end16 /= 1000000000;
        return tree;

    }

    public void encrypt(BloomFilterIndex.BFINode<Integer> root) {
        List<double[]> ress = new ArrayList<>();
        if (!root.isLeaf()) {

            c = new double[root.value.size()];

            for (int i = 0; i < root.value.size(); i++) {

                c[i] = root.value.getBitSet().getWord(i);
            }
            ress.add(c);
            root.value = null;
            root.value2 = ress;

            for (BloomFilterIndex.BFINode<Integer> g : root.children) {
                encrypt(g);
            }
        } else {

            double[] y = new double[100];
            double[] z = new double[100];
            Vector<Double> m6 = new Vector<Double>(100);
            Vector<Double> n1 = new Vector<Double>(100);

            for (int i = 0; i < s.size(); i++) {

                if (s.get(i) == 1) {
                    m6.add((double) root.value.getBitSet().getWord(i));
                    n1.add((double) root.value.getBitSet().getWord(i));
                } else {
                    m6.add(0.5 * (root.value.getBitSet().getWord(i)) + m5);
                    n1.add(0.5 * (root.value.getBitSet().getWord(i)) + m5);
                }
            }

            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    y[i] += a2[i][j] * m6.get(j);
                    z[i] += b2[i][j] * n1.get(j);
                }
            }
            ress.add(y);
            ress.add(z);
            root.value = null;
            root.value2 = ress;
        }

    }

    public Vector<String> search(Hashtable h, double[] r1, double[] r2) {
        long startTime = 0;
        double endEncryptedLinearTime = 0.0;
        int siMax = 0;
        Vector<String> r = new Vector<String>();
        double sum;
        double sum1;
        int sum2;
        Hashtable<Integer, Vector<String>> hashTTTTT = new Hashtable<Integer, Vector<String>>();

        List< List<double[]>> vvv = new ArrayList<List<double[]>>(h.values());
        Set<Entry<String, List<double[]>>> entries = h.entrySet();
        startTime = System.currentTimeMillis();
        for (int d = 0; d < vvv.size(); d++) {
            sum = 0.0;
            sum1 = 0.0;
            sum2 = 0;
            String sd = null;

            List<double[]> v = vvv.get(d);
            for (Entry<String, List<double[]>> ent : entries) {
                if (ent.getValue().equals(v)) {
                    sd = ent.getKey();
                }

            }
            double[] v1 = v.get(0);
            double[] v2 = v.get(1);

            for (int j = 0; j < v1.length; j++) {
                sum += (v1[j]) * (r1[j]);
                sum1 += (v2[j]) * (r2[j]);

            }
            sum2 = (int) (sum + sum1);

            if (!hashTTTTT.containsKey(sum2)) {
                hashTTTTT.put(sum2, new Vector<String>());
                hashTTTTT.get(sum2).add(sd);
            } else {
                hashTTTTT.get(sum2).add(sd);
            }

        }

        Set<Integer> si = hashTTTTT.keySet();
        siMax = Collections.max(si);
        r = hashTTTTT.get(siMax);
        endEncryptedLinearTime = System.currentTimeMillis() - startTime;
        endEncryptedLinearTime /= 1000.0;
        System.out.println("searchEncryptedBloomFilterTime is :" + endEncryptedLinearTime + "" + "second");
        System.out.println("name of file contain keyword search from bloom filter list after encryption: " + Arrays.toString(r.toArray()));
        return r;

    }

    public List<String> searchTree(BloomFilterIndex trree, BloomFilter tr, Integer[] binaryHashResult) {
        long startTime = 0;
        double searchTreeTime = 0.0;
        int treeMax = 0;
        int treeMax1 = 0;
        List<String> ts = new ArrayList<>();
        BloomFilterIndex.BFINode<Integer> v;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < trree.root.children.size(); i++) {
            v = (BloomFilterIndex.BFINode<Integer>) trree.root.children.get(i);
            searchNode(v, tr, binaryHashResult);
        }

        Set<Integer> treeSum = tablee.keySet();
        treeMax = Collections.max(treeSum);
        ts = tablee.get(treeMax);
        searchTreeTime = System.currentTimeMillis() - startTime;
        searchTreeTime /= 1000.0;
        System.out.println(" search In Tree Before Encryption is :" + searchTreeTime + "" + " second");
        System.out.println("result of search in tree before encryption : " + Arrays.toString(ts.toArray()));

        return ts;
    }

    public void searchNode(BloomFilterIndex.BFINode<Integer> n, BloomFilter tr, Integer[] binaryHashResult) {

        int vvv = 0;
        if (n.isLeaf()) {
            BloomFilter br = n.value;
            String fileName = br.getName();
            for (int j = 0; j < br.size(); j++) {
                vvv += (br.getBitSet().getWord(j)) * (tr.getBitSet().getWord(j));
            }
            if (!tablee.containsKey(vvv)) {
                tablee.put(vvv, new ArrayList<String>());
                tablee.get(vvv).add(fileName);
            } else {
                tablee.get(vvv).add(fileName);
            }

        } else {

            BloomFilter<Integer> innerNode1 = n.value;
            if (innerNode1.containsAll(binaryHashResult)) {
                // if (innerNode1.contains(binaryHashResult) ) {
                for (int x = 0; x < n.children.size(); x++) {
                    BloomFilterIndex.BFINode<Integer> z = n.children.get(x);
                    searchNode(z, tr, binaryHashResult);
                }
            }
        }
    }

    public List<String> searchh(Integer[] binaryHashResult, double[] e, double[] k, BloomFilterIndex ttree) {

        long startTime = 0;
        double endEncryptedTreeTime = 0.0;
        List dd = new ArrayList<>();
        String maximumNode = null;
        String maximuumNode1 = null;
        String maximuumNode = null;
        int maximum = 0;
        int maximum1 = 0;
        int maximuum = 0;
        int maximuum1 = 0;
        BloomFilterIndex.BFINode<Integer> m;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < ttree.root.children.size(); i++) {
            m = (BloomFilterIndex.BFINode<Integer>) ttree.root.children.get(i);
            searchencryptedNode(binaryHashResult, e, k, m);

        }

        Set<Integer> allSummtion = hashTTTT.keySet();
        maximuum = Collections.max(allSummtion);
        dd = hashTTTT.get(maximuum);
        endEncryptedTreeTime = System.currentTimeMillis() - startTime;
        endEncryptedTreeTime /= 1000.0;
        System.out.println("searchEncryptedTreeTime is:" + endEncryptedTreeTime + "" + "second");

        System.out.println("result of search in encrypted tree is : " + Arrays.toString(dd.toArray()));

        return dd;

    }

    public void searchencryptedNode(Integer[] binaryHashResult, double[] e, double[] k, BloomFilterIndex.BFINode<Integer> m) {
        double sum = 0;
        double sum1 = 0;
        int sum2 = 0;
        if (m.isLeaf()) {

            List<double[]> s = m.value2;
            double[] s1 = s.get(0);
            double[] s2 = s.get(1);

            for (int d = 0; d < s1.length; d++) {
                sum += s1[d] * e[d];
                sum1 += s2[d] * k[d];

            }
            sum2 = (int) (sum + sum1);
            String g = m.getFileName();

            if (!hashTTTT.containsKey(sum2)) {
                hashTTTT.put(sum2, new ArrayList<String>());
                hashTTTT.get(sum2).add(g);
            } else {
                hashTTTT.get(sum2).add(g);
            }

        } else {
            double[] innerNode = m.value2.get(0);
            int mo = 0;
            int j;
            //  boolean gh=false;
            for (j = 0; j < binaryHashResult.length; j++) {
                if (innerNode[binaryHashResult[j]] == 1.0) {
                    mo++;
                }

            }

            if (mo == j) {
                for (int h = 0; h < m.children.size(); h++) {
                    BloomFilterIndex.BFINode<Integer> s = m.children.get(h);
                    searchencryptedNode(binaryHashResult, e, k, s);

                }

            }

        }
    }

    public List<String> innerProduct(List<BloomFilter> bfListt, BloomFilter ff) {
        int maxmaximum = 0;
        int maxmaximum1 = 0;
        int ddd;
        List<String> lis = new ArrayList();
        Hashtable<Integer, List<String>> hashhhTT = new Hashtable<Integer, List<String>>();

        startTime5 = System.currentTimeMillis();
        for (int i = 0; i < bfListt.size(); i++) {
            ddd = 0;

            BloomFilter fi = bfListt.get(i);
            String nameOfFile = fi.getName();
            for (int j = 0; j < fi.size(); j++) {

                ddd += ((int) fi.getBitSet().getWord(j)) * ((int) ff.getBitSet().getWord(j));
            }

            if (!hashhhTT.containsKey(ddd)) {
                hashhhTT.put(ddd, new ArrayList<String>());
                hashhhTT.get(ddd).add(nameOfFile);
            } else {
                hashhhTT.get(ddd).add(nameOfFile);
            }

        }

        Set<Integer> summtion = hashhhTT.keySet();
        maxmaximum = Collections.max(summtion);

        lis = hashhhTT.get(maxmaximum);

        endLinearSearchTime = System.currentTimeMillis() - startTime5;
        endLinearSearchTime /= 1000.0;
        System.out.println("linear search time is : " + endLinearSearchTime + "" + "second");

        System.out.println("inner product between query and list of bloom filters before encryption: " + Arrays.toString(lis.toArray()));

        return lis;

    }

    public static Vector customSplit(String src, int size) {

        Vector<String> vec = new Vector<String>(20);
        for (int i = 0; i < src.length() - 1; i++) {
            vec.add(src.substring(i, (i + size > src.length()) ? src.length() : i + size));
        }
        return vec;
    }

    public String display() {
        return "out1";
    }

    public String displaytree() {
        return st.toString();
    }

    public String displayfile() {
        return lineout;
    }
}
