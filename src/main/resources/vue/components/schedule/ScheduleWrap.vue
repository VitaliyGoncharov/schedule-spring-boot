<template>
  <div id="edit-schedule-content">
    <b-tabs content-class="mt-3" fill>
      <b-tab title="Up" active>
        <div class="table-responsive">
          <day-schedule-table v-for="(weekSchedule, propertyName) of schedule.up"
                    :key="propertyName" :daySchedules="weekSchedule"
                    :dayName="propertyName"
                    :week="'up'"
                    :groupId="schedule.groupId">
          </day-schedule-table>
        </div>
      </b-tab>

      <b-tab title="Down">
        <div class="table-responsive">
          <day-schedule-table v-for="(weekSchedule, propertyName) of schedule.down"
                    :key="propertyName" :daySchedules="weekSchedule"
                    :dayName="propertyName"
                    :week="'down'"
                    :groupId="schedule.groupId">
          </day-schedule-table>
        </div>
      </b-tab>
    </b-tabs>
			
    <edit-schedule-modal></edit-schedule-modal>
    <remove-schedule-modal></remove-schedule-modal>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'
import ScheduleService from '@/services/ScheduleService'
import EventBus from '@/EventBus'
import DayScheduleTable from '@/components/schedule/DayScheduleTable.vue'
import EditScheduleModal from '@/components/schedule/EditScheduleModal.vue'
import RemoveScheduleModal from '@/components/schedule/RemoveScheduleModal.vue'
import { mapState } from 'vuex';

@Component({
  components: {DayScheduleTable, EditScheduleModal, RemoveScheduleModal},
  computed: {
    ...mapState({
      schedule: (state: any) => state.scheduleStore.schedule
    })
  }
})
export default class ScheduleWrap extends Vue {

  created() {
    let _this = this;
		EventBus.$on('fetchSchedule', function(groupId) {
			_this.getSchedule(groupId);
		});
  }

  getSchedule(groupId) {
    this.$store.dispatch('getSchedule', groupId);
  }
}
</script>