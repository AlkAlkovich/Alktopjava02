<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>

<html>
<dandelion:bundle includes="topjavaDatatable"/>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><fmt:message key="meals.title"/></h3>

            <div class="view-box">
                <a class="btn btn-sm btn-info" id="add">Add meal</a>

                <datatables:table id="datatable" data="${mealList}" row="meal" theme="bootstrap3"
                                  cssClass="table table-striped" pageable="false" info="false">
                    <datatables:column title="Description" property="description">

                    </datatables:column>
                    <datatables:column title="Calories" property="calories" >

                    </datatables:column>
                    <datatables:column title="Date" property="dateTime">

                    </datatables:column>
                    <datatables:column filterable="false" sortable="false">
                        <a class="btn btn-xs btn-primary edit" id="${meal.id}">Edit</a>
                        <a class="btn btn-xs btn-danger delete" id="${meal.id}">Delete</a>
                    </datatables:column>
                </datatables:table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title">Add Meal</h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="item_id" name="item_id">

                    <div class="form-group">
                        <label for="datetime" class="control-label col-xs-3">Date</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control datetime-picker" id="datetime" name="datetime" placeholder="Date">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Description</label>
                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description"
                                   placeholder="description">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="calories" class="control-label col-xs-3">Calories</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="calories" name="calories"
                                   placeholder="calories">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var ajaxUrl = 'ajax/profile/meals/';
    //        $(document).ready(function () {
    $(function () {
        makeEditable();
    });
</script>
<script type="text/javascript">
    $(function () {
        $('#datetime').datetimepicker({

        });
    });
</script>
</html>
