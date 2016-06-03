# Groovy Stored Procedures

Making use of [Groovy Sql class](http://docs.groovy-lang.org/latest/html/api/groovy/sql/Sql.html) can make calling stored procedures much easier in a Java environment. This project provides use cases that can be expanding for CRUD in a JSF application. That was the motivation for this exercise. SAP (Sybase) ASE 15.7 was the DBMS for this project, but any DBMS that has stored procedures can be substituted.

1. Stored procedure with input parameter(s) and return value.
1. Stored procedure with input parameter(s), return value and one return parameter.
1. Stored procedure with input parameter(s), return value and one result set.

For cases 1 and 2 we use method **call** with the following syntax:

```
public void call(String sql,
        List<Object> params,
        Closure closure)
          throws Exception
```

For case 3  we use method **callWithRows** with the following syntax:

```
public List<GroovyRowResult> callWithRows(String sql,
                                 List<Object> params,
                                 Closure closure)
                                   throws SQLException
```

Since **callWithRows** and **callWithAllRows** methods are not working, for case 3 we will use the following which works for our use cases.

```
public List<GroovyRowResult> rows(String sql,
                         List<Object> params)
                           throws SQLException
```

## Problems

Getting empty result set when using callWithAllRows. Found this: [JIRA: callWithAllRows result set is empty](http://mail-archives.apache.org/mod_mbox/groovy-notifications/201603.mbox/%3CJIRA.12944763.1456458509000.36835.1457466640893@Atlassian.JIRA%3E)

```
John Wagenleitner commented on GROOVY-7768:
-------------------------------------------

I tested with Groovy 2.4.0 and 2.4.6 with the jtds-1.3.1 driver and it worked.  The user I
passed to {{sp_who}} was defined under both Logins for SQLServer and Users for the database
I connected to.  If you execute the procedure in a SQL Query sheet does it return rows?  Can
you provide more details on which db and driver you're using?

> groovy.sql.Sql callWithAllRows returns blank result when passing params
> -----------------------------------------------------------------------
>
>                 Key: GROOVY-7768
>                 URL: https://issues.apache.org/jira/browse/GROOVY-7768
>             Project: Groovy
>          Issue Type: Bug
>          Components: SQL processing
>    Affects Versions: 2.4.0
>            Reporter: Ryan Mills
>   Original Estimate: 5h
>  Remaining Estimate: 5h
>
> callWithAllRows works with regular sql, and an empty list.
> call works with a map or list
> Howver, callWithAllRows returns an empty list when using with params eg.)
> List list = new ArrayList();
> l.add("myid");
> sql.callWithAllRows("sp_who ?", list, {});
> result is []



--
This message was sent by Atlassian JIRA
(v6.3.4#6332)
```


## References

1. [Simpler Stored Procedures with Groovy](https://objectpartners.com/2014/01/24/simpler-stored-procedures-with-groovy/)
1. [Groovy API Documentation](http://docs.groovy-lang.org/latest/html/gapi/)
