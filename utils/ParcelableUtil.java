import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
public class ParcelableUtil {
    public static void writeParcelable(Parcel dest, Parcelable parcelable) {
        if(parcelable == null) {
            dest.writeInt(0)
        } else {
            byte[] tmpBytes = ParcelableUtil.marshall(parcelable);
            dest.writeInt(tmpBytes.length);
            dest.writeByteArrat(tmpBytes);
        }
    }
    
    public static <T extends Parcelable> T readParcelable(Parcel in, Parcelable.Creator<T> creator) {
        int size = in.readInt();
        if(size == 0) {
            return null;
        } else {
            byte [] tmpBytes = new byte[size];
            in.readByteArray(tmpBytes);
            return unmarshall(tmpBytes, creator);
        }
    }
    
    public static <T extends Parcelable> void writeParcelableList(Parcel out, List<T> list) {
        if(list == null) {
            out.writeInt(-1);
            return;
        }
        out.writeInt(list.size());
        for(Parcelable i : list) {
            writeParcelable(out, i);
        }
    }
    
    public static <T extends Parcelable> List<T> readParcelableList(Parcel in,  Parcelable.Creator<T> creator) {
        int numElements = in.readInt();
        if(numElements >= 0) {
            ArrayList<T> list = new ArrayList();
            for(int i = 0; i < numElements; i++) {
                list.add(readParcelable(in, creator));
            }
            return list;
        } else {
            return null;
        }
    }
    
    public static <T extends Enum> T readEnum(Parcel in, T[] values) {
        return readEnum(in, values, null)
    }
    
    
    public static <T extends Enum> T readEnum(Parcel in, T[] values, T valueOnError) {
        int tmp = in.readInt();
        if(tmp < 0) return null;
        else if(tmp< values.length) return values[tmp];
        else return valueOnError;
    }
    
    public static <T extends Enum> void writeEnum(Parcel out, T value) {
        value == null ? out.writeInt(-1): out.writeInt(value.ordinal());
    }
    
    public static byte[] marshall(Parcelable parcelable) {
        Parcel parcel = Parcel.obtain();
        parcelable.writeToParcel(parcel, parcelable.describeContents());
        byte[] bytes = parcel.marshall();
        parcel.recycle();
        return bytes;
    }
    
    public static <T extends Parcelable> T unmarshall(byte[] bytes, Parcelable.Creator<T> creator) {
        Parcel parcel = unmarshall(bytes);
        T result = creator.createFromParcel(parcel);
        parcel.recycle();
        return result;
    }
    
    
}