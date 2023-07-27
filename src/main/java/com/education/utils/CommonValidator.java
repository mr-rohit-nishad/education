package com.education.utils; 
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
@Component
public class CommonValidator {
public static boolean isEmpty( String string ){
if( string == null || string.trim().length() == 0 ){
return true;
}
return false;
}

public static boolean isNull(Object obj)
  {
  if(obj!=null)
  return false;
  return true;
  }
 
 
  public static boolean isEmpty(List<?> list)
  {
  if(list!=null && !list.isEmpty())
  return false;
  return true;
  }
/**
* This method returns true if the collection is null or is empty.
* @param collection
* @return true | false
*/
public static boolean isEmpty( Collection<?> collection ){
if( collection == null || collection.isEmpty() ){
return true;
}
return false;
}
/**
* This method returns true of the map is null or is empty.
* @param map
* @return true | false
*/
public static boolean isEmpty( Map<?, ?> map ){
if( map == null || map.isEmpty() ){
return true;
}
return false;
}
/**
* This method returns true if the object is null.
* @param object
* @return true | false
*/
public static boolean isEmpty( Object object ){
if( object == null ){
return true;
}
return false;
}
}