/**This code is developed by: Meetakshi Setiya
 * Roll number: 2019253
 * Section A, Group 2
 * CSE112 Computer Organisation End-semester assignment
 */

// System design: word size = 1 byte, 32 bit machine
//default values in the cache at uninitialised offset address = -1

import java.util.*;
public class MeetakshiSetiya_2019253_FinalAssignment {
    static int index = -1;
    boolean check_power_of_2(int num)
    {
        if(num==0)
            return false;
        else
            return((int)(Math.floor(((Math.log(num)/Math.log(2)))))== (int)(Math.ceil(((Math.log(num)/Math.log(2))))));
    }

    int find_min_index(int n, int lru[], int actual_start_index)
    {
        int min = actual_start_index;
        for(int i=1;i<n;i++)
        {
            if(lru[i+actual_start_index]<lru[min])
                min = i+actual_start_index;
        }
        return min;
    }

    void write_data(String[] tag, int[][] data, int n, int offset, String address, int inp, int[] lru)
    {

        int index_num = 0;
        int indx = (int)(Math.log(tag.length/n)/Math.log(2));  //number of bits that represent tag array length after n associative
        int index_bits = (int)(Math.log(tag.length)/Math.log(2)); //number of bits that represent tag array length originally
        int offset_num = Integer.parseInt(address.substring(32-offset),2); //offset
        String ind_str = address.substring(32-offset-index_bits, 32-offset); //index of the original tag array in string
        if(indx>0)
            index_num = Integer.parseInt(ind_str.substring(ind_str.length()-indx),2); //index of the n-associative tag array
        String tag_str = address.substring(0, 32-offset-indx);
        //System.out.println(tag+" "+ind_str+" "+offset_num);
        int actual_start_write_index = index_num*n;
        for(int i=0;i<n;i++)
        {
            if (tag[i + actual_start_write_index] != null && tag[i + actual_start_write_index].equals(tag_str)) {
                index++;
                lru[i+actual_start_write_index]=index;
                //if that block is already loaded into the memory. Edit the contents at the requisite offset
                data[i + actual_start_write_index][offset_num] = inp;
                return;
            } else if (tag[i + actual_start_write_index] == null) {
                index++;
                lru[i+actual_start_write_index]=index;
                //if some block is empty
                tag[i + actual_start_write_index] = tag_str;
                data[i + actual_start_write_index][offset_num] = inp;
                return;
            }
        }
        //if index is occupied
        int earliest = find_min_index(n, lru, actual_start_write_index);
        tag[earliest]=tag_str;
        Arrays.fill(data[earliest],-1);
        data[earliest][offset_num] = inp;
        index++;
        lru[earliest]= index;
        return;
    }

    void read_data(String tag[], int[][] data, int n, int offset, String address, int[] lru) {
        if (address.length() < 32) {
            for (int i = address.length() - 1; i < 31; i++)
                address = '0' + address;
        }
        int offset_num = Integer.parseInt(address.substring(32 - offset), 2);
        int index_num = 0;
        int indx = (int) (Math.log(tag.length / n) / Math.log(2));  //number of bits that represent tag array length after n associative
        int index_bits = (int) (Math.log(tag.length) / Math.log(2));
        String ind_str = address.substring(32 - offset - index_bits, 32 - offset); //index of the original tag array in string
        if (indx > 0)
            index_num = Integer.parseInt(ind_str.substring(ind_str.length() - indx), 2);
        int actual_start_write_index = index_num * n;
        String tag_str = address.substring(0, 32 - offset - indx);
        for (int i = 0; i < n; i++) {
            if (tag[i + actual_start_write_index] != null && tag[i + actual_start_write_index].equals(tag_str)) {
                System.out.println("\nCache Hit");
                index++;
                lru[i+actual_start_write_index]=index;
                System.out.println("Data at " + address + " ----> " + data[i + actual_start_write_index][offset_num]);
                return;
            }
        }
        System.out.println("\nCache Miss");
    }

    //calculate the offset, start index
    void display_cache(String[] tag, int[][] data, int offset, int n)
    {
        for (int i = 0; i < tag.length; i++)
        {
            if (tag[i] != null)
            {
                System.out.println("\t\tAddress\t          ---> data\n");
                for (int j = 0; j < (int) Math.pow(2, offset); j++)
                {
                    String ind="";
                    if(n!=tag.length) {
                        ind = Integer.toBinaryString(i / n);
                        if (ind.length() < (int) (Math.log(tag.length / n) / Math.log(2))) {
                            for (int k = ind.length(); k < (int) (Math.log(tag.length / n) / Math.log(2)); k++) {
                                ind = '0' + ind;
                            }
                        }
                    }

                    String off = Integer.toBinaryString(j);
                    if (off.length() < offset) {
                        for (int k = off.length(); k < offset; k++) {
                            off = '0' + off;
                        }
                    }
                    if(n!=tag.length)
                        System.out.println(tag[i]+ ind+ off + "  ---> " + data[i][j]);
                    else
                        System.out.println(tag[i] + off + "  ---> " + data[i][j]);
                }
                System.out.println();
                System.out.println("_________________________________________________________________________________________________________________________________________________________________________________");
                System.out.println();
            }
        }
    }
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        MeetakshiSetiya_2019253_FinalAssignment obj = new MeetakshiSetiya_2019253_FinalAssignment();
        System.out.println("Enter the cache size and block size in bytes. Both of them must be some power of 2 and non 0");
        int S = in.nextInt();
        int B = in.nextInt();
        if(!obj.check_power_of_2(S) || !obj.check_power_of_2(B) || S==0 || B==0)
        {
            throw new IllegalArgumentException("Size of the cache and block size must be some power of 2 and non 0. Restart the program and try again.");
        }
        if(S<=B)
        {
            throw new IllegalArgumentException("Size of the cache must be greater than the block size. Restart the program and try again.");
        }
        int n=1;
        System.out.println("\nEnter the number corresponding to the the desired mapping method");
        System.out.println("1. Direct mapping\n2. Fully Associative mapping\n3. N-way Set Associative mapping ");
        int chr = in.nextInt();
        if(chr!=1 && chr!=2 && chr!=3)
        {
            throw new IllegalArgumentException("Invalid option. Restart the program and try again.");
        }
        int offset = (int) (Math.log(B)/Math.log(2));
        int number_of_blocks = S/B;
        if(chr==1)
            n=1;
        else if(chr==2)
            n=number_of_blocks;
        else if(chr==3)
        {
            System.out.println("Enter the desired 'N' for N-way set Associative mapping. N must be a multiple of 2 and must be less than the number of cache blocks "+number_of_blocks);
            n = in.nextInt();
            if(!obj.check_power_of_2(n) || n>number_of_blocks || n<1)
            {
                throw new IllegalArgumentException("Invalid input. Restart the program and try again.");
            }
        }
        String[] tag = new String[number_of_blocks];
        int[][] data = new int[number_of_blocks][(int)Math.pow(2,offset)];
        int lru[] = new int[number_of_blocks];

        Arrays.fill(tag, null);
        Arrays.fill(lru, Integer.MAX_VALUE);
        for(int i=0;i<number_of_blocks;i++)
            Arrays.fill(data[i], -1);

        while(true)
        {
            System.out.println("\nEnter the character corresponding to the desired action\nW. Write to the cache\nR. Read from the cache\nD. Display the entire cache\nQ. Quit");
            char format = in.next().charAt(0);
            if(format == 'Q'|| format=='q') {
                System.out.println("\nSuccessfully quit");
                break;
            }
            else if(format == 'W' || format == 'w')
            {
                System.out.println("\nEnter a 32 bit binary address and the integer data to be stored (maximum 1 byte data i.e. number 0 to 255)");
                String address = in.next();
                if (address.length() >= 33) {
                    throw new IllegalArgumentException("Address length must be less than 32 bits. Restart the program and try again.");
                }
                for(int i=0;i<address.length();i++)
                {
                    if(address.charAt(i)!='0'&& address.charAt(i)!='1')
                    {
                        throw new IllegalArgumentException("Invalid binary address. Restart the program and try again.");
                    }
                }
                if (address.length() < 32)
                {
                    for (int i = address.length() - 1; i < 31; i++)
                        address = '0' + address;
                }
                int d = in.nextInt();
                if(d<0||d>255)
                {
                    throw new IllegalArgumentException("Invalid data. Restart the program and try again.");
                }
                obj.write_data(tag, data, n, offset, address, d, lru);
                System.out.println(d+" successfully written at "+address);
            }
            else if(format == 'R'|| format == 'r')
            {
                System.out.println("\nEnter a 32 bit binary address");
                String address = in.next();
                if (address.length() > 32) {
                    throw new IllegalArgumentException("Address length must be less than 32 bits. Restart the program and try again.");
                }
                for(int i=0;i<address.length();i++)
                {
                    if(address.charAt(i)!='0'&& address.charAt(i)!='1')
                    {
                        throw new IllegalArgumentException("Invalid binary address. Restart the program and try again.");
                    }
                }
                obj.read_data(tag, data, n, offset, address, lru);
            }
            else if(format == 'D'||format=='d')
            {
                obj.display_cache(tag, data, offset, n);
            }
            else
                System.out.println("Wrong input. Try again");
        }

    }
}

