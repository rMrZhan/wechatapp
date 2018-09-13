package top.jianghuling.wechatapp.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MissionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MissionExample() {
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

        public Criteria andMissionIdIsNull() {
            addCriterion("mission_id is null");
            return (Criteria) this;
        }

        public Criteria andMissionIdIsNotNull() {
            addCriterion("mission_id is not null");
            return (Criteria) this;
        }

        public Criteria andMissionIdEqualTo(String value) {
            addCriterion("mission_id =", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdNotEqualTo(String value) {
            addCriterion("mission_id <>", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdGreaterThan(String value) {
            addCriterion("mission_id >", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdGreaterThanOrEqualTo(String value) {
            addCriterion("mission_id >=", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdLessThan(String value) {
            addCriterion("mission_id <", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdLessThanOrEqualTo(String value) {
            addCriterion("mission_id <=", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdLike(String value) {
            addCriterion("mission_id like", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdNotLike(String value) {
            addCriterion("mission_id not like", value, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdIn(List<String> values) {
            addCriterion("mission_id in", values, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdNotIn(List<String> values) {
            addCriterion("mission_id not in", values, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdBetween(String value1, String value2) {
            addCriterion("mission_id between", value1, value2, "missionId");
            return (Criteria) this;
        }

        public Criteria andMissionIdNotBetween(String value1, String value2) {
            addCriterion("mission_id not between", value1, value2, "missionId");
            return (Criteria) this;
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

        public Criteria andTakerIdIsNull() {
            addCriterion("taker_id is null");
            return (Criteria) this;
        }

        public Criteria andTakerIdIsNotNull() {
            addCriterion("taker_id is not null");
            return (Criteria) this;
        }

        public Criteria andTakerIdEqualTo(String value) {
            addCriterion("taker_id =", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdNotEqualTo(String value) {
            addCriterion("taker_id <>", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdGreaterThan(String value) {
            addCriterion("taker_id >", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdGreaterThanOrEqualTo(String value) {
            addCriterion("taker_id >=", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdLessThan(String value) {
            addCriterion("taker_id <", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdLessThanOrEqualTo(String value) {
            addCriterion("taker_id <=", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdLike(String value) {
            addCriterion("taker_id like", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdNotLike(String value) {
            addCriterion("taker_id not like", value, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdIn(List<String> values) {
            addCriterion("taker_id in", values, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdNotIn(List<String> values) {
            addCriterion("taker_id not in", values, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdBetween(String value1, String value2) {
            addCriterion("taker_id between", value1, value2, "takerId");
            return (Criteria) this;
        }

        public Criteria andTakerIdNotBetween(String value1, String value2) {
            addCriterion("taker_id not between", value1, value2, "takerId");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeIsNull() {
            addCriterion("accept_time is null");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeIsNotNull() {
            addCriterion("accept_time is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeEqualTo(Date value) {
            addCriterion("accept_time =", value, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeNotEqualTo(Date value) {
            addCriterion("accept_time <>", value, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeGreaterThan(Date value) {
            addCriterion("accept_time >", value, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("accept_time >=", value, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeLessThan(Date value) {
            addCriterion("accept_time <", value, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeLessThanOrEqualTo(Date value) {
            addCriterion("accept_time <=", value, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeIn(List<Date> values) {
            addCriterion("accept_time in", values, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeNotIn(List<Date> values) {
            addCriterion("accept_time not in", values, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeBetween(Date value1, Date value2) {
            addCriterion("accept_time between", value1, value2, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andAcceptTimeNotBetween(Date value1, Date value2) {
            addCriterion("accept_time not between", value1, value2, "acceptTime");
            return (Criteria) this;
        }

        public Criteria andMissionStateIsNull() {
            addCriterion("mission_state is null");
            return (Criteria) this;
        }

        public Criteria andMissionStateIsNotNull() {
            addCriterion("mission_state is not null");
            return (Criteria) this;
        }

        public Criteria andMissionStateEqualTo(Byte value) {
            addCriterion("mission_state =", value, "missionState");
            return (Criteria) this;
        }

        public Criteria andMissionStateNotEqualTo(Byte value) {
            addCriterion("mission_state <>", value, "missionState");
            return (Criteria) this;
        }

        public Criteria andMissionStateGreaterThan(Byte value) {
            addCriterion("mission_state >", value, "missionState");
            return (Criteria) this;
        }

        public Criteria andMissionStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("mission_state >=", value, "missionState");
            return (Criteria) this;
        }

        public Criteria andMissionStateLessThan(Byte value) {
            addCriterion("mission_state <", value, "missionState");
            return (Criteria) this;
        }

        public Criteria andMissionStateLessThanOrEqualTo(Byte value) {
            addCriterion("mission_state <=", value, "missionState");
            return (Criteria) this;
        }

        public Criteria andMissionStateIn(List<Byte> values) {
            addCriterion("mission_state in", values, "missionState");
            return (Criteria) this;
        }

        public Criteria andMissionStateNotIn(List<Byte> values) {
            addCriterion("mission_state not in", values, "missionState");
            return (Criteria) this;
        }

        public Criteria andMissionStateBetween(Byte value1, Byte value2) {
            addCriterion("mission_state between", value1, value2, "missionState");
            return (Criteria) this;
        }

        public Criteria andMissionStateNotBetween(Byte value1, Byte value2) {
            addCriterion("mission_state not between", value1, value2, "missionState");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNull() {
            addCriterion("finish_time is null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNotNull() {
            addCriterion("finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeEqualTo(Date value) {
            addCriterion("finish_time =", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotEqualTo(Date value) {
            addCriterion("finish_time <>", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThan(Date value) {
            addCriterion("finish_time >", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("finish_time >=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThan(Date value) {
            addCriterion("finish_time <", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("finish_time <=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIn(List<Date> values) {
            addCriterion("finish_time in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotIn(List<Date> values) {
            addCriterion("finish_time not in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeBetween(Date value1, Date value2) {
            addCriterion("finish_time between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("finish_time not between", value1, value2, "finishTime");
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