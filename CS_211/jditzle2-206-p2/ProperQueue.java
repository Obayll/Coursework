public class ProperQueue{
  private int size;
  private Integer[] elements;
  
  public ProperQueue(int maxCapacity){
    if (maxCapacity < 0) elements = new Integer[0];
    else elements = new Integer[maxCapacity];
  }
  //\\
  public int getSize(){
    size = 0;
    for(int i = 0;i < elements.length;i++){
      if(elements[i] != null) size++;
    }
    return size;
  }
  
  public int getCapacity(){
    return elements.length;
  }
  //\\
  public boolean isFull(){
    for(int i = 0;i < elements.length;i++){
      if(elements[i] == null) return false;
    }
    return true;
  }
  //\\
  public boolean isEmpty(){
    if (elements.length == 0) return true;
    for(int i = 0;i < elements.length;i++){
      if(elements[i] != null) return false;
    }
    return true;
  }
  //\\
  public String toString(){
    String result = "";
    if (elements.length == 0) return result;
    for(int i = 0;i < elements.length;i++){
      if (elements[i] != null) result += elements[i] + " ";
    }
    if (result == "") return result;
    return result.substring(0,result.length()-1);
  }
  //
  public boolean add (Integer e){
    if ((elements.length == 0) || (elements[elements.length-1] != null)) throw new RuntimeException("Queue full");
    if (e == null) throw new RuntimeException("Cannot add null");
    for(int i = 0;i < elements.length;i++){
      if(elements[i] == null){
        elements[i] = e;
        return true;
      }
    }
    return false;
  }
  //\\
  public boolean offer (Integer e){
    if (e == null) return false;
    for(int i = 0;i < elements.length;i++){
      if(elements[i] == null){
        elements[i] = e;
        return true;
      }
    }
    return false;
  }
  
  public Integer remove(){
    int var = 0;
    if (elements[0] == null) throw new RuntimeException("Queue empty");
    var = elements[0];
    for(int i = 0;i < elements.length-1;i++){
      elements[i] = elements[i+1];
      elements[i+1] = null;
    }
    return var;
  }
  
  public Integer poll(){
    int var = 0;
    if (elements[0] == null) return null;
    var = elements[0];
    for(int i = 0;i < elements.length-1;i++){
      elements[i] = elements[i+1];
      elements[i+1] = null;
    }
    return var;
  }
  
  public Integer element(){
    if (elements[0] == null) throw new RuntimeException("Queue empty");
    return elements[0];
  }
  
  public Integer peek(){
    if (elements[0] == null) return null;
    return elements[0];
  }
  
}