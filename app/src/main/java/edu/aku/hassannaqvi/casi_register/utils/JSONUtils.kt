package edu.aku.hassannaqvi.casi_register.utils

import org.json.JSONException
import org.json.JSONObject

    fun mergeJSONObjects(Obj1: JSONObject, Obj2: JSONObject): JSONObject {
        val merged = JSONObject()
        val objs = arrayOf(Obj1, Obj2)
        for (obj in objs) {
            val it: Iterator<*> = obj.keys()
            while (it.hasNext()) {
                val key = it.next() as String
                try {
                    merged.put(key, obj[key])
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
        return merged
    }