package top.jianghuling.wechatapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCTime(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                timeList.add(new java.sql.Time(iter.next().getTime()));
            }
            addCriterion(condition, timeList, property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("order_id like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("order_id not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeIsNull() {
            addCriterion("goods_code is null");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeIsNotNull() {
            addCriterion("goods_code is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeEqualTo(String value) {
            addCriterion("goods_code =", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeNotEqualTo(String value) {
            addCriterion("goods_code <>", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeGreaterThan(String value) {
            addCriterion("goods_code >", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeGreaterThanOrEqualTo(String value) {
            addCriterion("goods_code >=", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeLessThan(String value) {
            addCriterion("goods_code <", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeLessThanOrEqualTo(String value) {
            addCriterion("goods_code <=", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeLike(String value) {
            addCriterion("goods_code like", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeNotLike(String value) {
            addCriterion("goods_code not like", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeIn(List<String> values) {
            addCriterion("goods_code in", values, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeNotIn(List<String> values) {
            addCriterion("goods_code not in", values, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeBetween(String value1, String value2) {
            addCriterion("goods_code between", value1, value2, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeNotBetween(String value1, String value2) {
            addCriterion("goods_code not between", value1, value2, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andRewardIsNull() {
            addCriterion("reward is null");
            return (Criteria) this;
        }

        public Criteria andRewardIsNotNull() {
            addCriterion("reward is not null");
            return (Criteria) this;
        }

        public Criteria andRewardEqualTo(Float value) {
            addCriterion("reward =", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardNotEqualTo(Float value) {
            addCriterion("reward <>", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardGreaterThan(Float value) {
            addCriterion("reward >", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardGreaterThanOrEqualTo(Float value) {
            addCriterion("reward >=", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardLessThan(Float value) {
            addCriterion("reward <", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardLessThanOrEqualTo(Float value) {
            addCriterion("reward <=", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardIn(List<Float> values) {
            addCriterion("reward in", values, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardNotIn(List<Float> values) {
            addCriterion("reward not in", values, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardBetween(Float value1, Float value2) {
            addCriterion("reward between", value1, value2, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardNotBetween(Float value1, Float value2) {
            addCriterion("reward not between", value1, value2, "reward");
            return (Criteria) this;
        }

        public Criteria andHostNameIsNull() {
            addCriterion("host_name is null");
            return (Criteria) this;
        }

        public Criteria andHostNameIsNotNull() {
            addCriterion("host_name is not null");
            return (Criteria) this;
        }

        public Criteria andHostNameEqualTo(String value) {
            addCriterion("host_name =", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameNotEqualTo(String value) {
            addCriterion("host_name <>", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameGreaterThan(String value) {
            addCriterion("host_name >", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameGreaterThanOrEqualTo(String value) {
            addCriterion("host_name >=", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameLessThan(String value) {
            addCriterion("host_name <", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameLessThanOrEqualTo(String value) {
            addCriterion("host_name <=", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameLike(String value) {
            addCriterion("host_name like", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameNotLike(String value) {
            addCriterion("host_name not like", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameIn(List<String> values) {
            addCriterion("host_name in", values, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameNotIn(List<String> values) {
            addCriterion("host_name not in", values, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameBetween(String value1, String value2) {
            addCriterion("host_name between", value1, value2, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameNotBetween(String value1, String value2) {
            addCriterion("host_name not between", value1, value2, "hostName");
            return (Criteria) this;
        }

        public Criteria andTakeAddressIsNull() {
            addCriterion("take_address is null");
            return (Criteria) this;
        }

        public Criteria andTakeAddressIsNotNull() {
            addCriterion("take_address is not null");
            return (Criteria) this;
        }

        public Criteria andTakeAddressEqualTo(String value) {
            addCriterion("take_address =", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressNotEqualTo(String value) {
            addCriterion("take_address <>", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressGreaterThan(String value) {
            addCriterion("take_address >", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressGreaterThanOrEqualTo(String value) {
            addCriterion("take_address >=", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressLessThan(String value) {
            addCriterion("take_address <", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressLessThanOrEqualTo(String value) {
            addCriterion("take_address <=", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressLike(String value) {
            addCriterion("take_address like", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressNotLike(String value) {
            addCriterion("take_address not like", value, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressIn(List<String> values) {
            addCriterion("take_address in", values, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressNotIn(List<String> values) {
            addCriterion("take_address not in", values, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressBetween(String value1, String value2) {
            addCriterion("take_address between", value1, value2, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andTakeAddressNotBetween(String value1, String value2) {
            addCriterion("take_address not between", value1, value2, "takeAddress");
            return (Criteria) this;
        }

        public Criteria andDestinationIsNull() {
            addCriterion("destination is null");
            return (Criteria) this;
        }

        public Criteria andDestinationIsNotNull() {
            addCriterion("destination is not null");
            return (Criteria) this;
        }

        public Criteria andDestinationEqualTo(String value) {
            addCriterion("destination =", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationNotEqualTo(String value) {
            addCriterion("destination <>", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationGreaterThan(String value) {
            addCriterion("destination >", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationGreaterThanOrEqualTo(String value) {
            addCriterion("destination >=", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationLessThan(String value) {
            addCriterion("destination <", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationLessThanOrEqualTo(String value) {
            addCriterion("destination <=", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationLike(String value) {
            addCriterion("destination like", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationNotLike(String value) {
            addCriterion("destination not like", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationIn(List<String> values) {
            addCriterion("destination in", values, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationNotIn(List<String> values) {
            addCriterion("destination not in", values, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationBetween(String value1, String value2) {
            addCriterion("destination between", value1, value2, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationNotBetween(String value1, String value2) {
            addCriterion("destination not between", value1, value2, "destination");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightIsNull() {
            addCriterion("goods_weight is null");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightIsNotNull() {
            addCriterion("goods_weight is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightEqualTo(String value) {
            addCriterion("goods_weight =", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightNotEqualTo(String value) {
            addCriterion("goods_weight <>", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightGreaterThan(String value) {
            addCriterion("goods_weight >", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightGreaterThanOrEqualTo(String value) {
            addCriterion("goods_weight >=", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightLessThan(String value) {
            addCriterion("goods_weight <", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightLessThanOrEqualTo(String value) {
            addCriterion("goods_weight <=", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightLike(String value) {
            addCriterion("goods_weight like", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightNotLike(String value) {
            addCriterion("goods_weight not like", value, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightIn(List<String> values) {
            addCriterion("goods_weight in", values, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightNotIn(List<String> values) {
            addCriterion("goods_weight not in", values, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightBetween(String value1, String value2) {
            addCriterion("goods_weight between", value1, value2, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andGoodsWeightNotBetween(String value1, String value2) {
            addCriterion("goods_weight not between", value1, value2, "goodsWeight");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNull() {
            addCriterion("starttime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("starttime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(Date value) {
            addCriterionForJDBCTime("starttime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(Date value) {
            addCriterionForJDBCTime("starttime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(Date value) {
            addCriterionForJDBCTime("starttime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("starttime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(Date value) {
            addCriterionForJDBCTime("starttime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("starttime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<Date> values) {
            addCriterionForJDBCTime("starttime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<Date> values) {
            addCriterionForJDBCTime("starttime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("starttime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("starttime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andDeadlineIsNull() {
            addCriterion("deadline is null");
            return (Criteria) this;
        }

        public Criteria andDeadlineIsNotNull() {
            addCriterion("deadline is not null");
            return (Criteria) this;
        }

        public Criteria andDeadlineEqualTo(Date value) {
            addCriterionForJDBCTime("deadline =", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotEqualTo(Date value) {
            addCriterionForJDBCTime("deadline <>", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineGreaterThan(Date value) {
            addCriterionForJDBCTime("deadline >", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("deadline >=", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineLessThan(Date value) {
            addCriterionForJDBCTime("deadline <", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("deadline <=", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineIn(List<Date> values) {
            addCriterionForJDBCTime("deadline in", values, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotIn(List<Date> values) {
            addCriterionForJDBCTime("deadline not in", values, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("deadline between", value1, value2, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("deadline not between", value1, value2, "deadline");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIsNull() {
            addCriterion("release_time is null");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIsNotNull() {
            addCriterion("release_time is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeEqualTo(Date value) {
            addCriterion("release_time =", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotEqualTo(Date value) {
            addCriterion("release_time <>", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeGreaterThan(Date value) {
            addCriterion("release_time >", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("release_time >=", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeLessThan(Date value) {
            addCriterion("release_time <", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeLessThanOrEqualTo(Date value) {
            addCriterion("release_time <=", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIn(List<Date> values) {
            addCriterion("release_time in", values, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotIn(List<Date> values) {
            addCriterion("release_time not in", values, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeBetween(Date value1, Date value2) {
            addCriterion("release_time between", value1, value2, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotBetween(Date value1, Date value2) {
            addCriterion("release_time not between", value1, value2, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andExpressTypeIsNull() {
            addCriterion("express_type is null");
            return (Criteria) this;
        }

        public Criteria andExpressTypeIsNotNull() {
            addCriterion("express_type is not null");
            return (Criteria) this;
        }

        public Criteria andExpressTypeEqualTo(String value) {
            addCriterion("express_type =", value, "expressType");
            return (Criteria) this;
        }

        public Criteria andExpressTypeNotEqualTo(String value) {
            addCriterion("express_type <>", value, "expressType");
            return (Criteria) this;
        }

        public Criteria andExpressTypeGreaterThan(String value) {
            addCriterion("express_type >", value, "expressType");
            return (Criteria) this;
        }

        public Criteria andExpressTypeGreaterThanOrEqualTo(String value) {
            addCriterion("express_type >=", value, "expressType");
            return (Criteria) this;
        }

        public Criteria andExpressTypeLessThan(String value) {
            addCriterion("express_type <", value, "expressType");
            return (Criteria) this;
        }

        public Criteria andExpressTypeLessThanOrEqualTo(String value) {
            addCriterion("express_type <=", value, "expressType");
            return (Criteria) this;
        }

        public Criteria andExpressTypeLike(String value) {
            addCriterion("express_type like", value, "expressType");
            return (Criteria) this;
        }

        public Criteria andExpressTypeNotLike(String value) {
            addCriterion("express_type not like", value, "expressType");
            return (Criteria) this;
        }

        public Criteria andExpressTypeIn(List<String> values) {
            addCriterion("express_type in", values, "expressType");
            return (Criteria) this;
        }

        public Criteria andExpressTypeNotIn(List<String> values) {
            addCriterion("express_type not in", values, "expressType");
            return (Criteria) this;
        }

        public Criteria andExpressTypeBetween(String value1, String value2) {
            addCriterion("express_type between", value1, value2, "expressType");
            return (Criteria) this;
        }

        public Criteria andExpressTypeNotBetween(String value1, String value2) {
            addCriterion("express_type not between", value1, value2, "expressType");
            return (Criteria) this;
        }

        public Criteria andReleaserIdIsNull() {
            addCriterion("releaser_id is null");
            return (Criteria) this;
        }

        public Criteria andReleaserIdIsNotNull() {
            addCriterion("releaser_id is not null");
            return (Criteria) this;
        }

        public Criteria andReleaserIdEqualTo(String value) {
            addCriterion("releaser_id =", value, "releaserId");
            return (Criteria) this;
        }

        public Criteria andReleaserIdNotEqualTo(String value) {
            addCriterion("releaser_id <>", value, "releaserId");
            return (Criteria) this;
        }

        public Criteria andReleaserIdGreaterThan(String value) {
            addCriterion("releaser_id >", value, "releaserId");
            return (Criteria) this;
        }

        public Criteria andReleaserIdGreaterThanOrEqualTo(String value) {
            addCriterion("releaser_id >=", value, "releaserId");
            return (Criteria) this;
        }

        public Criteria andReleaserIdLessThan(String value) {
            addCriterion("releaser_id <", value, "releaserId");
            return (Criteria) this;
        }

        public Criteria andReleaserIdLessThanOrEqualTo(String value) {
            addCriterion("releaser_id <=", value, "releaserId");
            return (Criteria) this;
        }

        public Criteria andReleaserIdLike(String value) {
            addCriterion("releaser_id like", value, "releaserId");
            return (Criteria) this;
        }

        public Criteria andReleaserIdNotLike(String value) {
            addCriterion("releaser_id not like", value, "releaserId");
            return (Criteria) this;
        }

        public Criteria andReleaserIdIn(List<String> values) {
            addCriterion("releaser_id in", values, "releaserId");
            return (Criteria) this;
        }

        public Criteria andReleaserIdNotIn(List<String> values) {
            addCriterion("releaser_id not in", values, "releaserId");
            return (Criteria) this;
        }

        public Criteria andReleaserIdBetween(String value1, String value2) {
            addCriterion("releaser_id between", value1, value2, "releaserId");
            return (Criteria) this;
        }

        public Criteria andReleaserIdNotBetween(String value1, String value2) {
            addCriterion("releaser_id not between", value1, value2, "releaserId");
            return (Criteria) this;
        }

        public Criteria andHostPhoneIsNull() {
            addCriterion("host_phone is null");
            return (Criteria) this;
        }

        public Criteria andHostPhoneIsNotNull() {
            addCriterion("host_phone is not null");
            return (Criteria) this;
        }

        public Criteria andHostPhoneEqualTo(String value) {
            addCriterion("host_phone =", value, "hostPhone");
            return (Criteria) this;
        }

        public Criteria andHostPhoneNotEqualTo(String value) {
            addCriterion("host_phone <>", value, "hostPhone");
            return (Criteria) this;
        }

        public Criteria andHostPhoneGreaterThan(String value) {
            addCriterion("host_phone >", value, "hostPhone");
            return (Criteria) this;
        }

        public Criteria andHostPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("host_phone >=", value, "hostPhone");
            return (Criteria) this;
        }

        public Criteria andHostPhoneLessThan(String value) {
            addCriterion("host_phone <", value, "hostPhone");
            return (Criteria) this;
        }

        public Criteria andHostPhoneLessThanOrEqualTo(String value) {
            addCriterion("host_phone <=", value, "hostPhone");
            return (Criteria) this;
        }

        public Criteria andHostPhoneLike(String value) {
            addCriterion("host_phone like", value, "hostPhone");
            return (Criteria) this;
        }

        public Criteria andHostPhoneNotLike(String value) {
            addCriterion("host_phone not like", value, "hostPhone");
            return (Criteria) this;
        }

        public Criteria andHostPhoneIn(List<String> values) {
            addCriterion("host_phone in", values, "hostPhone");
            return (Criteria) this;
        }

        public Criteria andHostPhoneNotIn(List<String> values) {
            addCriterion("host_phone not in", values, "hostPhone");
            return (Criteria) this;
        }

        public Criteria andHostPhoneBetween(String value1, String value2) {
            addCriterion("host_phone between", value1, value2, "hostPhone");
            return (Criteria) this;
        }

        public Criteria andHostPhoneNotBetween(String value1, String value2) {
            addCriterion("host_phone not between", value1, value2, "hostPhone");
            return (Criteria) this;
        }

        public Criteria andOrderStateIsNull() {
            addCriterion("order_state is null");
            return (Criteria) this;
        }

        public Criteria andOrderStateIsNotNull() {
            addCriterion("order_state is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStateEqualTo(Byte value) {
            addCriterion("order_state =", value, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateNotEqualTo(Byte value) {
            addCriterion("order_state <>", value, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateGreaterThan(Byte value) {
            addCriterion("order_state >", value, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("order_state >=", value, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateLessThan(Byte value) {
            addCriterion("order_state <", value, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateLessThanOrEqualTo(Byte value) {
            addCriterion("order_state <=", value, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateIn(List<Byte> values) {
            addCriterion("order_state in", values, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateNotIn(List<Byte> values) {
            addCriterion("order_state not in", values, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateBetween(Byte value1, Byte value2) {
            addCriterion("order_state between", value1, value2, "orderState");
            return (Criteria) this;
        }

        public Criteria andOrderStateNotBetween(Byte value1, Byte value2) {
            addCriterion("order_state not between", value1, value2, "orderState");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andReleaserDelTagIsNull() {
            addCriterion("releaser_del_tag is null");
            return (Criteria) this;
        }

        public Criteria andReleaserDelTagIsNotNull() {
            addCriterion("releaser_del_tag is not null");
            return (Criteria) this;
        }

        public Criteria andReleaserDelTagEqualTo(Byte value) {
            addCriterion("releaser_del_tag =", value, "releaserDelTag");
            return (Criteria) this;
        }

        public Criteria andReleaserDelTagNotEqualTo(Byte value) {
            addCriterion("releaser_del_tag <>", value, "releaserDelTag");
            return (Criteria) this;
        }

        public Criteria andReleaserDelTagGreaterThan(Byte value) {
            addCriterion("releaser_del_tag >", value, "releaserDelTag");
            return (Criteria) this;
        }

        public Criteria andReleaserDelTagGreaterThanOrEqualTo(Byte value) {
            addCriterion("releaser_del_tag >=", value, "releaserDelTag");
            return (Criteria) this;
        }

        public Criteria andReleaserDelTagLessThan(Byte value) {
            addCriterion("releaser_del_tag <", value, "releaserDelTag");
            return (Criteria) this;
        }

        public Criteria andReleaserDelTagLessThanOrEqualTo(Byte value) {
            addCriterion("releaser_del_tag <=", value, "releaserDelTag");
            return (Criteria) this;
        }

        public Criteria andReleaserDelTagIn(List<Byte> values) {
            addCriterion("releaser_del_tag in", values, "releaserDelTag");
            return (Criteria) this;
        }

        public Criteria andReleaserDelTagNotIn(List<Byte> values) {
            addCriterion("releaser_del_tag not in", values, "releaserDelTag");
            return (Criteria) this;
        }

        public Criteria andReleaserDelTagBetween(Byte value1, Byte value2) {
            addCriterion("releaser_del_tag between", value1, value2, "releaserDelTag");
            return (Criteria) this;
        }

        public Criteria andReleaserDelTagNotBetween(Byte value1, Byte value2) {
            addCriterion("releaser_del_tag not between", value1, value2, "releaserDelTag");
            return (Criteria) this;
        }

        public Criteria andTakerDelTagIsNull() {
            addCriterion("taker_del_tag is null");
            return (Criteria) this;
        }

        public Criteria andTakerDelTagIsNotNull() {
            addCriterion("taker_del_tag is not null");
            return (Criteria) this;
        }

        public Criteria andTakerDelTagEqualTo(Byte value) {
            addCriterion("taker_del_tag =", value, "takerDelTag");
            return (Criteria) this;
        }

        public Criteria andTakerDelTagNotEqualTo(Byte value) {
            addCriterion("taker_del_tag <>", value, "takerDelTag");
            return (Criteria) this;
        }

        public Criteria andTakerDelTagGreaterThan(Byte value) {
            addCriterion("taker_del_tag >", value, "takerDelTag");
            return (Criteria) this;
        }

        public Criteria andTakerDelTagGreaterThanOrEqualTo(Byte value) {
            addCriterion("taker_del_tag >=", value, "takerDelTag");
            return (Criteria) this;
        }

        public Criteria andTakerDelTagLessThan(Byte value) {
            addCriterion("taker_del_tag <", value, "takerDelTag");
            return (Criteria) this;
        }

        public Criteria andTakerDelTagLessThanOrEqualTo(Byte value) {
            addCriterion("taker_del_tag <=", value, "takerDelTag");
            return (Criteria) this;
        }

        public Criteria andTakerDelTagIn(List<Byte> values) {
            addCriterion("taker_del_tag in", values, "takerDelTag");
            return (Criteria) this;
        }

        public Criteria andTakerDelTagNotIn(List<Byte> values) {
            addCriterion("taker_del_tag not in", values, "takerDelTag");
            return (Criteria) this;
        }

        public Criteria andTakerDelTagBetween(Byte value1, Byte value2) {
            addCriterion("taker_del_tag between", value1, value2, "takerDelTag");
            return (Criteria) this;
        }

        public Criteria andTakerDelTagNotBetween(Byte value1, Byte value2) {
            addCriterion("taker_del_tag not between", value1, value2, "takerDelTag");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}