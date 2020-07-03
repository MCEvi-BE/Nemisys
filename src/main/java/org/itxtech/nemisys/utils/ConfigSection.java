package org.itxtech.nemisys.utils;

import java.util.*;


public class ConfigSection extends LinkedHashMap<String, Object> {


    public ConfigSection() {
        super();
    }


    public ConfigSection(String key, Object value) {
        this();
        this.set(key, value);
    }


    public ConfigSection(LinkedHashMap<String, Object> map) {
        this();
        if (map == null || map.isEmpty()) return;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof LinkedHashMap) {
                super.put(entry.getKey(), new ConfigSection((LinkedHashMap) entry.getValue()));
            } else {
                super.put(entry.getKey(), entry.getValue());
            }
        }
    }


    public Map<String, Object> getAllMap() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.putAll(this);
        return map;
    }



    public ConfigSection getAll() {
        return new ConfigSection(this);
    }


    public Object get(String key) {
        return this.get(key, null);
    }


    public <T> T get(String key, T defaltValue) {
        if (key == null || key.isEmpty()) return defaltValue;
        if (super.containsKey(key)) return (T) super.get(key);
        String[] keys = key.split("\\.", 2);
        if (!super.containsKey(keys[0])) return defaltValue;
        Object value = super.get(keys[0]);
        if (value != null && value instanceof ConfigSection) {
            ConfigSection section = (ConfigSection) value;
            return (T) section.get(keys[1], defaltValue);
        }
        return defaltValue;
    }


    public void set(String key, Object value) {
        String[] subKeys = key.split("\\.", 2);
        if (subKeys.length > 1) {
            ConfigSection childSection = new ConfigSection();
            if (this.containsKey(subKeys[0]) && super.get(subKeys[0]) instanceof ConfigSection)
                childSection = (ConfigSection) super.get(subKeys[0]);
            childSection.set(subKeys[1], value);
            super.put(subKeys[0], childSection);
        } else super.put(subKeys[0], value);
    }


    public boolean isSection(String key) {
        Object value = this.get(key);
        return value instanceof ConfigSection;
    }


    public ConfigSection getSection(String key) {
        return this.get(key, new ConfigSection());
    }



    public ConfigSection getSections() {
        return getSections(null);
    }


    public ConfigSection getSections(String key) {
        ConfigSection sections = new ConfigSection();
        ConfigSection parent = key == null || key.isEmpty() ? this.getAll() : getSection(key);
        if (parent == null) return sections;
        parent.entrySet().forEach(e -> {
            if (e.getValue() instanceof ConfigSection)
                sections.put(e.getKey(), (ConfigSection) e.getValue());
        });
        return sections;
    }


    public int getInt(String key) {
        return this.getInt(key, 0);
    }


    public int getInt(String key, int defaultValue) {
        return this.get(key, ((Number) defaultValue)).intValue();
    }


    public boolean isInt(String key) {
        Object val = get(key);
        return val instanceof Integer;
    }


    public long getLong(String key) {
        return this.getLong(key, 0);
    }


    public long getLong(String key, long defaultValue) {
        return this.get(key, ((Number) defaultValue)).longValue();
    }


    public boolean isLong(String key) {
        Object val = get(key);
        return val instanceof Long;
    }


    public double getDouble(String key) {
        return this.getDouble(key, 0);
    }


    public double getDouble(String key, double defaultValue) {
        return this.get(key, ((Number) defaultValue)).doubleValue();
    }


    public boolean isDouble(String key) {
        Object val = get(key);
        return val instanceof Double;
    }


    public String getString(String key) {
        return this.getString(key, "");
    }


    public String getString(String key, String defaultValue) {
        Object result = this.get(key, defaultValue);
        return String.valueOf(result);
    }


    public boolean isString(String key) {
        Object val = get(key);
        return val instanceof String;
    }


    public boolean getBoolean(String key) {
        return this.getBoolean(key, false);
    }


    public boolean getBoolean(String key, boolean defaultValue) {
        return this.get(key, (Boolean) defaultValue).booleanValue();
    }


    public boolean isBoolean(String key) {
        Object val = get(key);
        return val instanceof Boolean;
    }


    public List getList(String key) {
        return this.getList(key, null);
    }


    public List getList(String key, List defaultList) {
        return this.get(key, defaultList);
    }


    public boolean isList(String key) {
        Object val = get(key);
        return val instanceof List;
    }


    public List<String> getStringList(String key) {
        List value = this.getList(key);
        if (value == null) {
            return new ArrayList<>(0);
        }
        List<String> result = new ArrayList<>();
        for (Object o : value) {
            if (o instanceof String || o instanceof Number || o instanceof Boolean || o instanceof Character) {
                result.add(String.valueOf(o));
            }
        }
        return result;
    }


    public List<Integer> getIntegerList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Integer> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Integer) {
                result.add((Integer) object);
            } else if (object instanceof String) {
                try {
                    result.add(Integer.valueOf((String) object));
                } catch (Exception ex) {
                    //ignore
                }
            } else if (object instanceof Character) {
                result.add((int) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).intValue());
            }
        }
        return result;
    }


    public List<Boolean> getBooleanList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Boolean> result = new ArrayList<>();
        for (Object object : list) {
            if (object instanceof Boolean) {
                result.add((Boolean) object);
            } else if (object instanceof String) {
                if (Boolean.TRUE.toString().equals(object)) {
                    result.add(true);
                } else if (Boolean.FALSE.toString().equals(object)) {
                    result.add(false);
                }
            }
        }
        return result;
    }


    public List<Double> getDoubleList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Double> result = new ArrayList<>();
        for (Object object : list) {
            if (object instanceof Double) {
                result.add((Double) object);
            } else if (object instanceof String) {
                try {
                    result.add(Double.valueOf((String) object));
                } catch (Exception ex) {
                    //ignore
                }
            } else if (object instanceof Character) {
                result.add((double) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).doubleValue());
            }
        }
        return result;
    }


    public List<Float> getFloatList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Float> result = new ArrayList<>();
        for (Object object : list) {
            if (object instanceof Float) {
                result.add((Float) object);
            } else if (object instanceof String) {
                try {
                    result.add(Float.valueOf((String) object));
                } catch (Exception ex) {
                    //ignore
                }
            } else if (object instanceof Character) {
                result.add((float) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).floatValue());
            }
        }
        return result;
    }


    public List<Long> getLongList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Long> result = new ArrayList<>();
        for (Object object : list) {
            if (object instanceof Long) {
                result.add((Long) object);
            } else if (object instanceof String) {
                try {
                    result.add(Long.valueOf((String) object));
                } catch (Exception ex) {
                    //ignore
                }
            } else if (object instanceof Character) {
                result.add((long) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).longValue());
            }
        }
        return result;
    }


    public List<Byte> getByteList(String key) {
        List<?> list = getList(key);

        if (list == null) {
            return new ArrayList<Byte>(0);
        }

        List<Byte> result = new ArrayList<Byte>();

        for (Object object : list) {
            if (object instanceof Byte) {
                result.add((Byte) object);
            } else if (object instanceof String) {
                try {
                    result.add(Byte.valueOf((String) object));
                } catch (Exception ex) {
                    //ignore
                }
            } else if (object instanceof Character) {
                result.add((byte) ((Character) object).charValue());
            } else if (object instanceof Number) {
                result.add(((Number) object).byteValue());
            }
        }

        return result;
    }


    public List<Character> getCharacterList(String key) {
        List<?> list = getList(key);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Character> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Character) {
                result.add((Character) object);
            } else if (object instanceof String) {
                String str = (String) object;

                if (str.length() == 1) {
                    result.add(str.charAt(0));
                }
            } else if (object instanceof Number) {
                result.add((char) ((Number) object).intValue());
            }
        }

        return result;
    }


    public List<Short> getShortList(String key) {
        List<?> list = getList(key);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Short> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Short) {
                result.add((Short) object);
            } else if (object instanceof String) {
                try {
                    result.add(Short.valueOf((String) object));
                } catch (Exception ex) {
                    //ignore
                }
            } else if (object instanceof Character) {
                result.add((short) ((Character) object).charValue());
            } else if (object instanceof Number) {
                result.add(((Number) object).shortValue());
            }
        }

        return result;
    }


    public List<Map> getMapList(String key) {
        List<Map> list = getList(key);
        List<Map> result = new ArrayList<>();

        if (list == null) {
            return result;
        }

        for (Object object : list) {
            if (object instanceof Map) {
                result.add((Map) object);
            }
        }

        return result;
    }


    public boolean exists(String key, boolean ignoreCase) {
        if (ignoreCase) key = key.toLowerCase();
        for (String existKey : this.getKeys(true)) {
            if (ignoreCase) existKey = existKey.toLowerCase();
            if (existKey.equals(key)) return true;
        }
        return false;
    }


    public boolean exists(String key) {
        return exists(key, false);
    }


    public void remove(String key) {
        if (key == null || key.isEmpty()) return;
        if (super.containsKey(key)) super.remove(key);
        else if (this.containsKey(".")) {
            String[] keys = key.split("\\.", 2);
            if (super.get(keys[0]) instanceof ConfigSection) {
                ConfigSection section = (ConfigSection) super.get(keys[0]);
                section.remove(keys[1]);
            }
        }
    }


    public Set<String> getKeys(boolean child) {
        Set<String> keys = new LinkedHashSet<>();
        this.entrySet().forEach(entry -> {
            keys.add(entry.getKey());
            if (entry.getValue() instanceof ConfigSection) {
                if (child)
                    ((ConfigSection) entry.getValue()).getKeys(true).forEach(childKey -> keys.add(entry.getKey() + "." + childKey));
            }
        });
        return keys;
    }


    public Set<String> getKeys() {
        return this.getKeys(true);
    }
}