<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class SystemCode extends Model
{
    protected $table = "system_codes";

    public $timestamps = false;

    public function Users() {
        return $this->hasMany('App\User', 'user_type');
    }

    public function ProductPostings() {
        return $this->hasMany('App\ProductPosting', 'post_type');
    }
}
