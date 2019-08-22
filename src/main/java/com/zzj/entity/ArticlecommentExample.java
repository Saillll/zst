package com.zzj.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticlecommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticlecommentExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("a.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("a.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("a.id =", value, "a.id");
            return (Criteria) this;
        }
        public Criteria andUserIDEqualTo(String value) {
            addCriterion("user.id =", value, "user.id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("a.id <>", value, "a.id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("a.id >", value, "a.id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("a.id >=", value, "a.id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("a.id <", value, "a.id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("a.id <=", value, "a.id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("a.id like", value, "a.id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("a.id not like", value, "a.id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("a.id in", values, "a.id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("a.id not in", values, "a.id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("a.id between", value1, value2, "a.id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("a.id not between", value1, value2, "a.id");
            return (Criteria) this;
        }

        public Criteria andArticleidIsNull() {
            addCriterion("articleid is null");
            return (Criteria) this;
        }

        public Criteria andArticleidIsNotNull() {
            addCriterion("articleid is not null");
            return (Criteria) this;
        }

        public Criteria andArticleidEqualTo(String value) {
            addCriterion("articleid =", value, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidNotEqualTo(String value) {
            addCriterion("articleid <>", value, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidGreaterThan(String value) {
            addCriterion("articleid >", value, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidGreaterThanOrEqualTo(String value) {
            addCriterion("articleid >=", value, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidLessThan(String value) {
            addCriterion("articleid <", value, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidLessThanOrEqualTo(String value) {
            addCriterion("articleid <=", value, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidLike(String value) {
            addCriterion("articleid like", value, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidNotLike(String value) {
            addCriterion("articleid not like", value, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidIn(List<String> values) {
            addCriterion("articleid in", values, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidNotIn(List<String> values) {
            addCriterion("articleid not in", values, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidBetween(String value1, String value2) {
            addCriterion("articleid between", value1, value2, "articleid");
            return (Criteria) this;
        }

        public Criteria andArticleidNotBetween(String value1, String value2) {
            addCriterion("articleid not between", value1, value2, "articleid");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("parentid is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("parentid is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(String value) {
            addCriterion("parentid =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(String value) {
            addCriterion("parentid <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(String value) {
            addCriterion("parentid >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(String value) {
            addCriterion("parentid >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(String value) {
            addCriterion("parentid <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(String value) {
            addCriterion("parentid <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLike(String value) {
            addCriterion("parentid like", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotLike(String value) {
            addCriterion("parentid not like", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<String> values) {
            addCriterion("parentid in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<String> values) {
            addCriterion("parentid not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(String value1, String value2) {
            addCriterion("parentid between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(String value1, String value2) {
            addCriterion("parentid not between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andOriginuseridIsNull() {
            addCriterion("originuserid is null");
            return (Criteria) this;
        }

        public Criteria andOriginuseridIsNotNull() {
            addCriterion("originuserid is not null");
            return (Criteria) this;
        }

        public Criteria andOriginuseridEqualTo(String value) {
            addCriterion("originuserid =", value, "originuserid");
            return (Criteria) this;
        }

        public Criteria andOriginuseridNotEqualTo(String value) {
            addCriterion("originuserid <>", value, "originuserid");
            return (Criteria) this;
        }

        public Criteria andOriginuseridGreaterThan(String value) {
            addCriterion("originuserid >", value, "originuserid");
            return (Criteria) this;
        }

        public Criteria andOriginuseridGreaterThanOrEqualTo(String value) {
            addCriterion("originuserid >=", value, "originuserid");
            return (Criteria) this;
        }

        public Criteria andOriginuseridLessThan(String value) {
            addCriterion("originuserid <", value, "originuserid");
            return (Criteria) this;
        }

        public Criteria andOriginuseridLessThanOrEqualTo(String value) {
            addCriterion("originuserid <=", value, "originuserid");
            return (Criteria) this;
        }

        public Criteria andOriginuseridLike(String value) {
            addCriterion("originuserid like", value, "originuserid");
            return (Criteria) this;
        }

        public Criteria andOriginuseridNotLike(String value) {
            addCriterion("originuserid not like", value, "originuserid");
            return (Criteria) this;
        }

        public Criteria andOriginuseridIn(List<String> values) {
            addCriterion("originuserid in", values, "originuserid");
            return (Criteria) this;
        }

        public Criteria andOriginuseridNotIn(List<String> values) {
            addCriterion("originuserid not in", values, "originuserid");
            return (Criteria) this;
        }

        public Criteria andOriginuseridBetween(String value1, String value2) {
            addCriterion("originuserid between", value1, value2, "originuserid");
            return (Criteria) this;
        }

        public Criteria andOriginuseridNotBetween(String value1, String value2) {
            addCriterion("originuserid not between", value1, value2, "originuserid");
            return (Criteria) this;
        }

        public Criteria andOriginusernameIsNull() {
            addCriterion("originusername is null");
            return (Criteria) this;
        }

        public Criteria andOriginusernameIsNotNull() {
            addCriterion("originusername is not null");
            return (Criteria) this;
        }

        public Criteria andOriginusernameEqualTo(String value) {
            addCriterion("originusername =", value, "originusername");
            return (Criteria) this;
        }

        public Criteria andOriginusernameNotEqualTo(String value) {
            addCriterion("originusername <>", value, "originusername");
            return (Criteria) this;
        }

        public Criteria andOriginusernameGreaterThan(String value) {
            addCriterion("originusername >", value, "originusername");
            return (Criteria) this;
        }

        public Criteria andOriginusernameGreaterThanOrEqualTo(String value) {
            addCriterion("originusername >=", value, "originusername");
            return (Criteria) this;
        }

        public Criteria andOriginusernameLessThan(String value) {
            addCriterion("originusername <", value, "originusername");
            return (Criteria) this;
        }

        public Criteria andOriginusernameLessThanOrEqualTo(String value) {
            addCriterion("originusername <=", value, "originusername");
            return (Criteria) this;
        }

        public Criteria andOriginusernameLike(String value) {
            addCriterion("originusername like", value, "originusername");
            return (Criteria) this;
        }

        public Criteria andOriginusernameNotLike(String value) {
            addCriterion("originusername not like", value, "originusername");
            return (Criteria) this;
        }

        public Criteria andOriginusernameIn(List<String> values) {
            addCriterion("originusername in", values, "originusername");
            return (Criteria) this;
        }

        public Criteria andOriginusernameNotIn(List<String> values) {
            addCriterion("originusername not in", values, "originusername");
            return (Criteria) this;
        }

        public Criteria andOriginusernameBetween(String value1, String value2) {
            addCriterion("originusername between", value1, value2, "originusername");
            return (Criteria) this;
        }

        public Criteria andOriginusernameNotBetween(String value1, String value2) {
            addCriterion("originusername not between", value1, value2, "originusername");
            return (Criteria) this;
        }

        public Criteria andCommentsIsNull() {
            addCriterion("comments is null");
            return (Criteria) this;
        }

        public Criteria andCommentsIsNotNull() {
            addCriterion("comments is not null");
            return (Criteria) this;
        }

        public Criteria andCommentsEqualTo(String value) {
            addCriterion("comments =", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotEqualTo(String value) {
            addCriterion("comments <>", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThan(String value) {
            addCriterion("comments >", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThanOrEqualTo(String value) {
            addCriterion("comments >=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThan(String value) {
            addCriterion("comments <", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThanOrEqualTo(String value) {
            addCriterion("comments <=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLike(String value) {
            addCriterion("comments like", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotLike(String value) {
            addCriterion("comments not like", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsIn(List<String> values) {
            addCriterion("comments in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotIn(List<String> values) {
            addCriterion("comments not in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsBetween(String value1, String value2) {
            addCriterion("comments between", value1, value2, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotBetween(String value1, String value2) {
            addCriterion("comments not between", value1, value2, "comments");
            return (Criteria) this;
        }

        public Criteria andLikenumsIsNull() {
            addCriterion("likenums is null");
            return (Criteria) this;
        }

        public Criteria andLikenumsIsNotNull() {
            addCriterion("likenums is not null");
            return (Criteria) this;
        }

        public Criteria andLikenumsEqualTo(Long value) {
            addCriterion("likenums =", value, "likenums");
            return (Criteria) this;
        }

        public Criteria andLikenumsNotEqualTo(Long value) {
            addCriterion("likenums <>", value, "likenums");
            return (Criteria) this;
        }

        public Criteria andLikenumsGreaterThan(Long value) {
            addCriterion("likenums >", value, "likenums");
            return (Criteria) this;
        }

        public Criteria andLikenumsGreaterThanOrEqualTo(Long value) {
            addCriterion("likenums >=", value, "likenums");
            return (Criteria) this;
        }

        public Criteria andLikenumsLessThan(Long value) {
            addCriterion("likenums <", value, "likenums");
            return (Criteria) this;
        }

        public Criteria andLikenumsLessThanOrEqualTo(Long value) {
            addCriterion("likenums <=", value, "likenums");
            return (Criteria) this;
        }

        public Criteria andLikenumsIn(List<Long> values) {
            addCriterion("likenums in", values, "likenums");
            return (Criteria) this;
        }

        public Criteria andLikenumsNotIn(List<Long> values) {
            addCriterion("likenums not in", values, "likenums");
            return (Criteria) this;
        }

        public Criteria andLikenumsBetween(Long value1, Long value2) {
            addCriterion("likenums between", value1, value2, "likenums");
            return (Criteria) this;
        }

        public Criteria andLikenumsNotBetween(Long value1, Long value2) {
            addCriterion("likenums not between", value1, value2, "likenums");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("createdate is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("createdate is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterion("createdate =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterion("createdate <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterion("createdate >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("createdate >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterion("createdate <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterion("createdate <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterion("createdate in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterion("createdate not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterion("createdate between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterion("createdate not between", value1, value2, "createdate");
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